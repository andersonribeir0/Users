package alis.store.domain.repositories;

import alis.store.domain.entities.User;
import alis.store.domain.queries.QueryAllUsersResult;

import java.util.List;

public interface IUserRepository {
    boolean CheckIfEmailAlreadyExists(String email);
    boolean CheckIfDocumentAlreadyExists(String document);
    void AddUser(User user);
    List<QueryAllUsersResult> GetAll();
}
