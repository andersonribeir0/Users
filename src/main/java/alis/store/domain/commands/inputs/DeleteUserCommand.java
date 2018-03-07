package alis.store.domain.commands.inputs;

import alis.store.shared.commands.ICommand;

public class DeleteUserCommand implements ICommand{
    public String document;

    public DeleteUserCommand(String document){
        this.document = document;
    }
}
