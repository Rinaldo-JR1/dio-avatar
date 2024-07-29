package domain.repositories;

import domain.models.ProfilePhoto;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class S3ProfilePhotoStorageRepository implements ProfilePhotoStorageRepository {
    @Override
    public Uni<String> store(String customerId, ProfilePhoto profilePhoto) {
        return null;
    }
}
