package alis.store.domain.ValueObjects;

import javax.validation.constraints.NotNull;

public class Address {
    
    @NotNull(message = "Billing address is required")
    private String BillingAddress;
    
    @NotNull(message = "Home address is required")
    private String HomeAddress;

    public Address(String BillingAddress, String HomeAddress) {
        this.BillingAddress = BillingAddress;
        this.HomeAddress = HomeAddress;
    }

    public String getHomeAddress() {
        return HomeAddress;
    }

    public void setHomeAddress(String HomeAddress) {
        this.HomeAddress = HomeAddress;
    }

    public String getBillingAddress() {
        return BillingAddress;
    }

    public void setBillingAddress(String BillingAddress) {
        this.BillingAddress = BillingAddress;
    }
    
    @Override
    public String toString(){
        return HomeAddress;
    }
}
