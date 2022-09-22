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
    void setBillingAddress(IAddress billingAddress);
    void setShippingAddress(IAddress shippingAddress);

    //adding and removing contact info
    void addCustomerContact(ICustomerContact customerContact);
    void removeCustomerContact(ICustomerContact customerContact);

}
