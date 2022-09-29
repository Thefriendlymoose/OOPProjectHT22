package model.customer;

import java.util.List;

/**
 * A builder class for Customers
 * Every build must be initiated by "startBuild()"
 * buildCustomer() finalizes the build, nullifying the pointer to the Customer object
 * and returns the built Customer.
 */

public class CreateNewCustomer implements ICustomerBuilder{

    private IDGenerator<Integer> generator = new CustomerIDGenerator();
    private ICustomer customer;


    @Override
    public void startBuild() {
        this.customer = new Customer(generator.generateID());
    }

    @Override
    public void setCompanyName(String name) {
        customer.setCompanyName(name);
    }

    @Override
    public void setCompanyOrgNumber(int orgNumber) {
        customer.setCompanyOrgNumber(orgNumber);
    }

    @Override
    public void setBillingAddress(IAddress address) {
        customer.setBillingAddress(address);
    }

    @Override
    public void setShippingAddress(IAddress address) {
        customer.setShippingAddress(address);
    }

    @Override
    public void addCustomerContacts(List<ICustomerContact> contacts) {
        for (ICustomerContact c : contacts){
            customer.addCustomerContact(c);
        }
    }

    @Override
    public void addCustomerContacts(ICustomerContact contact) {
        customer.addCustomerContact(contact);
    }

    @Override
    public ICustomer buildCustomer() {
        ICustomer c = customer;
        customer = null;
        return c;
    }
}
