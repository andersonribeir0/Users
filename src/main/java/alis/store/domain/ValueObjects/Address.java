package alis.store.domain.ValueObjects;

public class Address {
    
    private String BillingAddress;
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
    
}
