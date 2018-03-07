package alis.store.domain.commands.outputs;

import alis.store.shared.commands.ICommandResult;

public class DeleteUserCommandResult implements ICommandResult {
    public String Document;
    public boolean Success;
}
