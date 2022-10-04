package model.customer;

import java.util.ArrayList;
import java.util.List;

public class Customer{

    private List<CustomerContact> customerContacts = new ArrayList<>();
    private Address billingAddress;
    private Address shippingAddress;
    private final long customerId;
    private int companyOrgNumber;
    private String companyName;

    public Customer(List<CustomerContact> customerContacts, Address billingAddress, Address shippingAddress, long customerId, int companyOrgNumber, String companyName) {
        this.companyOrgNumber = companyOrgNumber;
        this.customerContacts.addAll(customerContacts);
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.customerId = customerId;
        this.companyName = companyName;
    }

    public List<CustomerContact> getContacts() {
        return customerContacts;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public long getCustomerID() {
        return customerId;
    }

    public int getCompanyOrgNumber() {
        return companyOrgNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void addCustomerContact(CustomerContact customerContact) {
        customerContacts.add(customerContact);
    }

    public void removeCustomerContact(CustomerContact customerContact) {
        customerContacts.remove(customerContact);
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setCompanyOrgNumber(int companyOrgNumber) {
        this.companyOrgNumber = companyOrgNumber;
    }
}
