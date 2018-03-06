package alis.store.api.controllers;

import javax.net.ssl.SSLEngineResult.Status;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import alis.store.domain.commands.inputs.CreateUserCommand;
import alis.store.domain.commands.outputs.CreateUserCommandResult;
import alis.store.domain.handlers.UserHandler;
import alis.store.domain.repositories.IUserRepository;
import alis.store.shared.commands.ICommandHandler;
import alis.store.shared.commands.ICommandResult;


@RestController
public class UserController {

	@Autowired
	private IUserRepository repository;

    @RequestMapping("/")
    public ResponseEntity<ICommandResult> Add(@RequestBody @Valid CreateUserCommand command) throws Exception{
    	UserHandler handler = new UserHandler(repository);
    	ICommandResult commandResult = handler.Handle(command);        
        return new ResponseEntity<>(commandResult, HttpStatus.OK);
    }
    
}

