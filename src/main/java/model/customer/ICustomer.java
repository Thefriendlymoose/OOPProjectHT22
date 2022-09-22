package model.customer;

import java.util.List;

public interface ICustomer {

    List<ICustomerContact> getContacts();
    IAddress getBillingAddress();
    IAddress getShippingAddress();
    String getCustomerID();
    String getCompanyName();
}
