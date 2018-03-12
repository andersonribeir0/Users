package alis.store.api.application;

import alis.store.domain.entities.User;
import alis.store.domain.enums.EType;
import alis.store.domain.valueObjects.Address;
import alis.store.domain.valueObjects.Document;
import alis.store.domain.valueObjects.Email;
import alis.store.domain.valueObjects.Name;
import alis.store.infra.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan("alis.store")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
	    return args -> {
	        initUsers(userRepository, passwordEncoder);
        };
    }

	private void initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder){
		Document document = new Document("76583352311");
		Email email = new Email("abc@gmail.com");
		Name name = new Name("Anderson","Ribeiro");
		Address address = new Address("B Street", "A Street");
		User user = new User(name, address, email, document, EType.Admin, passwordEncoder.encode("123456"));
		userRepository.AddUser(user);
	}

}