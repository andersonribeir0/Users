package alis.store.api.controllers;

import javax.validation.Valid;

import alis.store.domain.commands.inputs.DeleteUserCommand;
import alis.store.domain.commands.inputs.UpdateUserCommand;
import alis.store.domain.commands.outputs.DeleteUserCommandResult;
import alis.store.domain.commands.outputs.UpdateUserCommandResult;
import alis.store.domain.entities.User;
import alis.store.domain.handlers.UserCreateHandler;
import alis.store.domain.handlers.UserRemoveHandler;
import alis.store.domain.handlers.UserUpdateHandler;
import alis.store.domain.queries.QueryUsersResult;
import alis.store.shared.commands.ICommand;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ResponseHeader;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import alis.store.domain.commands.inputs.CreateUserCommand;

import alis.store.domain.repositories.IUserRepository;
import alis.store.shared.commands.ICommandResult;

import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {

	private UserCreateHandler createHandler;
	private UserRemoveHandler removeHandler;
	private IUserRepository repository;
    private UserUpdateHandler updateHandler;

	@Autowired
	public UserController(UserUpdateHandler updateHandler, UserCreateHandler createHandler,UserRemoveHandler removeHandler, IUserRepository repository) {
		this.createHandler = createHandler;
		this.removeHandler = removeHandler;
		this.repository = repository;
		this.updateHandler = updateHandler;
	}
	
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Add new user")
    public ResponseEntity<ICommandResult> Add(@RequestBody @Valid CreateUserCommand command) {
    	try {
            ICommandResult commandResult = createHandler.Handle(command);
            return new ResponseEntity<>(commandResult, HttpStatus.OK);
        }catch(Exception e){
    	    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get all users")
    public ResponseEntity<List<QueryUsersResult>> GetAll(){
	    List<QueryUsersResult> result = repository.GetAll();
        if(result.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
	    return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{document}", method = RequestMethod.GET)
    @ApiOperation(value = "Get user by document")
    public ResponseEntity<QueryUsersResult> Get(@PathVariable String document){
        QueryUsersResult result = repository.GetByDocument(document);
        if (result == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{document}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete user by document")
    public ResponseEntity<ICommandResult> Delete(@PathVariable String document){
        DeleteUserCommand command = new DeleteUserCommand(document);
        DeleteUserCommandResult result = (DeleteUserCommandResult) removeHandler.Handle(command);
	    if(result.Success){
	        return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update user by id")
    public ResponseEntity<ICommandResult> Update(@PathVariable String id, @RequestBody @Valid UpdateUserCommand command) {
        try{
            QueryUsersResult query = repository.GetById(id);
            command.Id = query.Id;
            UpdateUserCommandResult result = (UpdateUserCommandResult) updateHandler.Handle(command);
            if(result.Success){
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}

