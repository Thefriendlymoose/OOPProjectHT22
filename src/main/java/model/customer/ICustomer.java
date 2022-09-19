package model.customer;

public interface ICustomer {
    IContacts getContacts();
    IAddress getAddress();
    String getCustomerID();
    String getName();
}
