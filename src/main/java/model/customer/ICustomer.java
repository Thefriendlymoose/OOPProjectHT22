package model.customer;

import java.util.List;

public interface ICustomer {

    // getters
    List<ICustomerContact> getContacts();
    IAddress getBillingAddress();
    IAddress getShippingAddress();
    String getCustomerID();
    String getCompanyName();

    //setters
    void setCompanyName(String companyName);
    void addCustomerContact(ICustomerContact customerContact);
    void setBillingAddress(IAddress billingAddress);
    void setShippingAddress(IAddress shippingAddress);

}
