package alis.store.api.controllers;

import alis.store.infra.security.JwtTokenUtil;
import alis.store.infra.security.JwtUser;
import alis.store.infra.security.services.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "auth")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value="refresh", method = RequestMethod.POST)
    public ResponseEntity<?> refreshToken(HttpServletResponse response){
        JwtUser user = JwtUserService.authenticated();
        String token = jwtTokenUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.ok().build();
    }
}
