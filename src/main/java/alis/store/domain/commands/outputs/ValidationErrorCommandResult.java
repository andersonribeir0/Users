package alis.store.domain.commands.outputs;

import alis.store.shared.commands.ICommandResult;

public class ValidationErrorCommandResult implements ICommandResult {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public ValidationErrorCommandResult(String message) {
        this.message = message;
    }
}
