package model.customer;

public class Customer implements ICustomer{
    private IContacts contactDetails;
    private IAddress address;
    private String customerID;
    private String name;

    public Customer(IContacts contactDetails, IAddress address, String customerID, String name){
        this.contactDetails = contactDetails;
        this.address = address;
        this.customerID = customerID;
        this.name = name;
    }

    @Override
    public IContacts getContacts() {
        return contactDetails;
    }

    @Override
    public IAddress getAddress() {
        return address;
    }

    @Override
    public String getCustomerID() {
        return customerID;
    }

    @Override
    public String getName() {
        return name;
    }
}
