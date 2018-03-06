package alis.store.api.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import alis.store.domain.commands.inputs.CreateUserCommand;
import alis.store.domain.handlers.UserHandler;
import alis.store.domain.repositories.IUserRepository;
import alis.store.shared.commands.ICommandResult;
import alis.store.domain.queries.QueryAllUsersResult;

import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {

	private UserHandler handler;
	private IUserRepository repository;

	@Autowired
	public UserController(UserHandler handler, IUserRepository repository) {
		this.handler = handler;
		this.repository = repository;
	}
	
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<ICommandResult> Add(@RequestBody @Valid CreateUserCommand command) {
    	try {
            ICommandResult commandResult = handler.Handle(command);
            return new ResponseEntity<>(commandResult, HttpStatus.OK);
        }catch(Exception e){
    	    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<QueryAllUsersResult>> GetAll(){
	    List<QueryAllUsersResult> result = repository.GetAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
}

