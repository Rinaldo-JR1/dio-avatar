package domain.repositories;

import domain.models.ProfilePhoto;

public interface ProfilePhotoRepositoryPersistence {
    void save(String customerId, ProfilePhoto profilePhoto);

}
