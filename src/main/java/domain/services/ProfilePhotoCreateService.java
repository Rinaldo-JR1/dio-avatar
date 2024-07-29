package domain.services;

import domain.models.ProfilePhoto;
import domain.repositories.ProfilePhotoRepositoryPersistence;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfilePhotoCreateService {
    private final ProfilePhotoRepositoryPersistence repository;
    public ProfilePhotoCreateService(ProfilePhotoRepositoryPersistence repository){
        this.repository = repository;
    }

    public void save(String customerId, ProfilePhoto profilePhoto){
        repository.save(customerId,profilePhoto);

    }
}
