package model.customer;

/**
 * Responsibility: a concrete strategy for setting an address in the editor
 * Used by: CustomerEditor
 * Uses: Customer
 * @author Simon Porsgaard / doktorjevksy
 */

public class BillingAddressStrategy implements AddressStrategy{

    private Customer customer;

    public BillingAddressStrategy(Customer customer){
        this.customer = customer;

    }
    @Override
    public void setAddress(Address address) {
        customer.setBillingAddress(address);
    }

    @Override
    public Address getAddress() {
        return customer.getBillingAddress();
    }
}
