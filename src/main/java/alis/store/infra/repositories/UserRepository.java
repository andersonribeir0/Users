package alis.store.infra.repositories;

import alis.store.domain.entities.User;
import alis.store.domain.queries.QueryAllUsersResult;
import alis.store.domain.repositories.IUserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepository implements IUserRepository{
    private List<User> users;
    
    @Autowired
    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public boolean CheckIfEmailAlreadyExists(String email) {
        for (User user : users){
            if (user.getEmail().toString().equals(email))
                return true;
        }
        return false;
    }

    public boolean CheckIfDocumentAlreadyExists(String document) {
        for (User user : users){
            if (user.getDocument().toString().equals(document))
                return true;
        }
        return false;
    }

    public void AddUser(User user) {
     	users.add(user);
    }

    public List<QueryAllUsersResult> GetAll(){
        List<QueryAllUsersResult> allUsers = new ArrayList<>();
        for (User user : users){
            QueryAllUsersResult qUser = new QueryAllUsersResult();
            qUser.Address = user.getAddress().toString();
            qUser.Document = user.getDocument().toString();
            qUser.Email = user.getEmail().toString();
            qUser.Name = user.getName().toString();
            allUsers.add(qUser);
        }
        return allUsers;
    }

}
