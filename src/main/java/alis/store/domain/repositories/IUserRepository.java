package alis.store.domain.repositories;

import alis.store.domain.entities.User;
import alis.store.domain.queries.QueryUsersResult;

import java.util.List;

public interface IUserRepository {
    boolean CheckIfEmailAlreadyExists(String email);
    boolean CheckIfDocumentAlreadyExists(String document);
    void AddUser(User user);
    List<QueryUsersResult> GetAll();
    QueryUsersResult GetByDocument(String document);
    boolean DeleteUser(String document);
    boolean UpdateUser(String id, User user);
    QueryUsersResult GetById(String id);
}
