package alis.store.infra.repositories;

import alis.store.domain.entities.User;
import alis.store.domain.queries.QueryUsersResult;
import alis.store.domain.repositories.IUserRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserRepository implements IUserRepository {
    private List<User> users;

    @Autowired
    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public boolean CheckIfEmailAlreadyExists(String email) {
        for (User user : users) {
            if (user.getEmail().toString().equals(email))
                return true;
        }
        return false;
    }

    public boolean CheckIfDocumentAlreadyExists(String document) {
        for (User user : users) {
            if (user.getDocument().toString().equals(document))
                return true;
        }
        return false;
    }

    public void AddUser(User user) {
        users.add(user);
    }

    public User getUserByDocument (String document){
        for (User user : users) {
            if (user.getDocument().toString().equals(document)) {
               return user;
            }
        }
        return null;
    }

    public List<QueryUsersResult> GetAll() {
        List<QueryUsersResult> allUsers = new ArrayList<>();
        for (User user : users) {
            QueryUsersResult qUser = new QueryUsersResult();
            qUser.Address = user.getAddress().toString();
            qUser.Document = user.getDocument().toString();
            qUser.Email = user.getEmail().toString();
            qUser.Name = user.getName().toString();
            qUser.Id = user.getId();
            allUsers.add(qUser);
        }
        return allUsers;
    }

    public QueryUsersResult GetByDocument(String document) {
        for (User user : users) {
            if (user.getDocument().toString().equals(document)) {
                QueryUsersResult result = new QueryUsersResult();
                result.Id = user.getId();
                result.Name = user.getName().toString();
                result.Address = user.getAddress().toString();
                result.Document = user.getDocument().toString();
                result.Email = user.getEmail().toString();
                return result;
            }
        }
        return null;
    }

    public QueryUsersResult GetById(String id){
        for (User user : users) {
            if (user.getId().toString().equals(id)) {
                QueryUsersResult result = new QueryUsersResult();
                result.Id = user.getId();
                result.Name = user.getName().toString();
                result.Address = user.getAddress().toString();
                result.Document = user.getDocument().toString();
                result.Email = user.getEmail().toString();
                return result;
            }
        }
        return null;
    }

    public boolean DeleteUser(String document) {
        for (Iterator<User> userIterator = users.listIterator(); userIterator.hasNext(); ) {
            User user = userIterator.next();
            if (user.getDocument().toString().equals(document)) {
                userIterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean UpdateUser(String id, User user){
        for (int i = 0 ; i < users.size(); i++){
            if(users.get(i).getId().equals(id)){
                users.set(i, user);
                return true;
            }
        }
        return false;
    }
}
