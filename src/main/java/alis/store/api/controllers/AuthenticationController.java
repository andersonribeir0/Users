package alis.store.api.controllers;

import alis.store.domain.entities.User;
import alis.store.domain.repositories.IUserRepository;
import alis.store.infra.security.CurrentUser;
import alis.store.infra.security.JwtAuthenticationRequest;
import alis.store.infra.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private IUserRepository repository;

    @PostMapping(value="/api/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getDocument(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authenticationRequest.getDocument());
        final User user = repository.getUserByDocument(authenticationRequest.getDocument());
        return ResponseEntity.ok(new CurrentUser(token, user));
    }

    @PostMapping(value="api/refresh")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String username = jwtTokenUtil.getUserNameFromToken(token);
        final User user = repository.getUserByDocument(username);

      //  if(jwtTokenUtil.canTokenBeRefreshed(token)){
      //      String refreshedToken = jwtTokenUtil.refreshToken(token);
      //      return ResponseEntity.ok(new CurrentUser(refreshedToken, user));
      //  } else {
           return ResponseEntity.badRequest().body(null);
      //  }
    }
}
