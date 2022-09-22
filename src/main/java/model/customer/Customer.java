package model.customer;

import java.util.List;

public class Customer implements ICustomer{
    @Override
    public List<ICustomerContact> getContacts() {
        return null;
    }

    @Override
    public IAddress getBillingAddress() {
        return null;
    }

    @Override
    public IAddress getShippingAddress() {
        return null;
    }

    @Override
    public String getCustomerID() {
        return null;
    }

    @Override
    public String getCompanyName() {
        return null;
    }

    @Override
    public void setCompanyName(String companyName) {

    }

    @Override
    public void addCustomerContact(ICustomerContact customerContact) {

    }

    @Override
    public void removeCustomerContact(ICustomerContact customerContact) {

    }

    @Override
    public void setBillingAddress(IAddress billingAddress) {

    }

    @Override
    public void setShippingAddress(IAddress shippingAddress) {

    }
}
