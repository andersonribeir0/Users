package alis.store.domain.commands.inputs;

import alis.store.domain.enums.EType;
import alis.store.shared.commands.ICommand;

public class UpdateUserCommand implements ICommand{
    public String Id;
    public String FirstName;
    public String LastName;
    public String Email;
    public String Document;
    public String HomeAddress;
    public String BillingAddress;
    public EType Type;
}
