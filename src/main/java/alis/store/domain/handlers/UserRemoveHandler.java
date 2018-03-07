package alis.store.domain.handlers;

import alis.store.domain.commands.inputs.DeleteUserCommand;
import alis.store.domain.commands.outputs.DeleteUserCommandResult;
import alis.store.domain.repositories.IUserRepository;
import alis.store.shared.commands.ICommandHandler;
import alis.store.shared.commands.ICommandResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRemoveHandler implements ICommandHandler<DeleteUserCommand>{
    private IUserRepository Repository;

    @Autowired
    public UserRemoveHandler(IUserRepository repository) {
        Repository = repository;
    }

    public ICommandResult Handle(DeleteUserCommand command){
        boolean result = Repository.DeleteUser(command.document);
        DeleteUserCommandResult deleteResult = new DeleteUserCommandResult();
        if(result == true){
            deleteResult.Success = false;
        }
        deleteResult.Document=command.document;
        deleteResult.Success=true;
        return deleteResult;
    }
}
