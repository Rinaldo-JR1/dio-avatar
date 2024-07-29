package domain.repositories;

import domain.models.ProfilePhoto;
import io.smallrye.mutiny.Uni;

public interface ProfilePhotoStorageRepository {
    Uni<String> sotre(String customerId, ProfilePhoto profilePhoto);
}
