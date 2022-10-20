package model.customer;

/**
 * Responsibility: Interface for different strategies that may be used when setting an address
 * Used by: CustomerEditor
 * Uses: -
 * @author Simon Porsgaard / doktorjevksy
 */
public interface AddressStrategy {

    void setAddress(Address address);
    Address getAddress();
}
