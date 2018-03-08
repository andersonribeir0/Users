package alis.store.infra.security;

import alis.store.domain.entities.User;
import alis.store.domain.enums.EType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwtUserFactory {

    private JwtUserFactory(){}

    public static JwtUser create(User user){
        return new JwtUser(
                user.getPassword(),
                user.getId(),
                user.getDocument().toString(),
                user.getEmail().toString(),
                user.getAddress().toString(),
                user.getName().toString(),
                mapToGrantedAuthorities(user.getType())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(EType userTypeEnum) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userTypeEnum.toString()));
        return authorities;
    }
}
