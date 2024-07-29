package infrastructure.repositories;

import domain.models.ProfilePhoto;

import java.util.Map;

import domain.repositories.ProfilePhotoRepository;
import jakarta.enterprise.context.RequestScoped;
import org.jboss.logging.Logger;
@RequestScoped
public class UnitOfWorkProfilePhotoRepository implements ProfilePhotoRepository {

    private final HibernateProfilePhotoRepositoryPersistence persistenceRepository;
    private final S3ProfilePhotoStorageRepository storageRepository;
    private Map<String, ProfilePhoto> entities;

    public UnitOfWorkProfilePhotoRepository(HibernateProfilePhotoRepositoryPersistence persistenceRepository,
                                            S3ProfilePhotoStorageRepository storageRepository) {
        this.persistenceRepository = persistenceRepository;
        this.storageRepository = storageRepository;
        this.entities = Map.of();
    }

    @Override
    public void registerEntities(Map<String, ProfilePhoto> entities) {
        this.entities = entities;
    }

    @Override
    public void commit() {
        entities.forEach((customerId, profilePhoto) -> {
            try {
                persistenceRepository.save(customerId, profilePhoto);
                var url = storageRepository.store(customerId, profilePhoto).await().indefinitely();
                var updated = new ProfilePhoto(profilePhoto.id(), url, profilePhoto.generatedPhoto());
                persistenceRepository.save(customerId, updated);
            } catch (Exception exception) {
                Logger.getLogger(getClass()).error(exception);
            }
        });
    }

    @Override
    public void rollback() {

    }
}
