package alis.store.infra.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;

public class JwtAuthenticationRequest implements Serializable{

    private static final long serialVersionUID = 1L;
    public String Document;
    public String Password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String document, String password){
        this.setPassword(password);
        this.setDocument(document);
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getDocument() {
        return Document;
    }

    public void setDocument(String document) {
        this.Document = document;
    }
}
