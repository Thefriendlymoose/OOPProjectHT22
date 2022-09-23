package model;

public class Customer {

    private String name;
    private int customerId;
    private int organisationNumber;
    private String customerContact;
    private Address billingAddress;
    private Address shippingAddress;

    public Customer(String name, int customerId, int organisationNumber, String customerContact, Address billingAddress, Address shippingAddress) {

        this.name = name;
        this.customerId = customerId;
        this.organisationNumber = organisationNumber;
        this.customerContact = customerContact;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getOrganisationNumber() {
        return organisationNumber;
    }

    public void setOrganisationNumber(int organisationNumber) {
        this.organisationNumber = organisationNumber;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
