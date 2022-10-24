package model.customer;

/**
 * Responsibility: a concrete strategy for setting an address in the editor
 * Sets and gets the Shipping Address
 * Used by: CustomerEditor
 * Uses: Customer
 * @author Simon Porsgaard / doktorjevksy
 */

public class ShippingAddressStrategy implements AddressStrategy{

    private Customer customer;

    public ShippingAddressStrategy(Customer customer){
        this.customer = customer;
    }

    @Override
    public void setAddress(Address address) {
        customer.setShippingAddress(address);
    }

    @Override
    public Address getAddress() {
        return customer.getShippingAddress();
    }
}
