package alis.store.infra.security.services;

import alis.store.domain.entities.User;
import alis.store.domain.queries.QueryUsersResult;
import alis.store.domain.repositories.IUserRepository;
import alis.store.domain.valueObjects.Name;
import alis.store.infra.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String document) throws UsernameNotFoundException {
       User user = repository.getUserByDocument(document);
       if (user == null){
           throw new UsernameNotFoundException(String.format("No user found with username '%s'."));
       } else {
           return JwtUserFactory.create(user);
       }
    }
}
