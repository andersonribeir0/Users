package alis.store.domain.commands.inputs;

import alis.store.shared.commands.ICommand;
import alis.store.domain.enums.EType;

public class CreateUserCommand implements ICommand {
    public String FirstName;
    public String LastName;
    public String Email;
    public String Document;
    public String HomeAddress;
    public String BillingAddress;
    public EType Type;
}
