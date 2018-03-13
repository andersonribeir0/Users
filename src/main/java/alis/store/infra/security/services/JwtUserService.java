package alis.store.infra.security.services;

import alis.store.infra.security.JwtUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtUserService {
    public static JwtUser authenticated() {
        try {
            return (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return null;
        }
    }
}
