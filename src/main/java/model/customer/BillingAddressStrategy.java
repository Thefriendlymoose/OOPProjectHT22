package model.customer;

public class BillingAddressStrategy implements AddressStrategy{

    private Customer customer;

    public BillingAddressStrategy(Customer customer){
        this.customer = customer;

    }
    @Override
    public void setAddress(Address address) {

    }

    @Override
    public Address getAddress() {
        return customer.getBillingAddress();
    }
}
