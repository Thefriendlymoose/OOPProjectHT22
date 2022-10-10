package model.customer;

public class ShippingAddressStrategy implements AddressStrategy{

    private Customer customer;

    public ShippingAddressStrategy(Customer customer){
        this.customer = customer;
    }

    @Override
    public void setAddress(Address address) {
        customer.setShippingAddress(address);
    }
}
