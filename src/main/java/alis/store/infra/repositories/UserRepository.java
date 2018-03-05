package alis.store.infra.repositories;

import alis.store.domain.entities.User;
import alis.store.domain.repositories.IUserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository{
    private List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public boolean CheckIfEmailAlreadyExists(String email) {
        for (User user : users){
            if (user.getEmail().toString() == email)
                return true;
        }
        return false;
    }

    public boolean CheckIfDocumentAlreadyExists(String document) {
        for (User user : users){
            if (user.getDocument().toString() == document)
                return true;
        }
        return false;
    }

    public void AddUser(User user) {
        users.add(user);
    }

    public List<User> GetAll(){
        return users;
    }

}
