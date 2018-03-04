package alis.store.shared.commands;

public interface ICommandHandler<T extends ICommand>{
    ICommandResult Handle(T command) throws Exception;
}
