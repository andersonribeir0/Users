package alis.store.domain.repositories;

import alis.store.domain.entities.User;

public interface IUserRepository {
    boolean CheckIfEmailAlreadyExists(String email);
    boolean CheckIfDocumentAlreadyExists(String document);
    void AddUser(User user);
}
