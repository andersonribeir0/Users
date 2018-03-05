package alis.store.domain.repositories;

import alis.store.domain.entities.User;

import java.util.List;

public interface IUserRepository {
    boolean CheckIfEmailAlreadyExists(String email);
    boolean CheckIfDocumentAlreadyExists(String document);
    void AddUser(User user);
    List<User> GetAll();
}
