package model.customer;

import java.util.ArrayList;
import java.util.List;

public class Customer{

    private List<CustomerContact> customerContacts = new ArrayList<>();
    private Address billingAddress = new Address();
    private Address shippingAddress = new Address();
    private long customerId;
    private long companyOrgNumber;
    private String companyName;

    public Customer(List<CustomerContact> customerContacts, Address billingAddress, Address shippingAddress, long customerId, int companyOrgNumber, String companyName) {
        this.companyOrgNumber = companyOrgNumber;
        this.customerContacts.addAll(customerContacts);
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.customerId = customerId;
        this.companyName = companyName;
    }

    public Customer(long customerId, long companyOrgNumber){
        this.customerId = customerId;
        this.companyOrgNumber = companyOrgNumber;
    }

    public Customer(long customerId){
        this.customerId = customerId;
    }


    // getters
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

    public long getCompanyOrgNumber() {
        return companyOrgNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    // setters

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

    public void setCompanyOrgNumber(long companyOrgNumber) {
        this.companyOrgNumber = companyOrgNumber;
    }

}
