package model.customer;

public class Customer implements ICustomer{
    private IContacts contactDetails;
    private IAddress address;
    private CustomerPojo pojo;

    public Customer(ContactsPojo contactsPojo, AddressPojo addressPojo, CustomerPojo contactPojo){
        this.contactDetails = new Contacts(contactsPojo);
        this.address = new Address(addressPojo);
        pojo = contactPojo;
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
    public String getCustomerID() { return pojo.getCustomerID().toString(); }

    @Override
    public String getName() { return pojo.getCompanyName(); }
}
