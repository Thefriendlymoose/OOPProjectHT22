package model.customer;

import java.util.ArrayList;
import java.util.List;

public class Customer implements ICustomer{

    private List<ICustomerContact> customerContacts = new ArrayList<>();
    private IAddress billingAddress;
    private IAddress shippingAddress;
    private final int customerId;
    private int companyOrgNumber;
    private String companyName;

    public Customer(List<ICustomerContact> customerContacts, IAddress billingAddress, IAddress shippingAddress, int customerId, int companyOrgNumber, String companyName) {
        this.companyOrgNumber = companyOrgNumber;
        this.customerContacts.addAll(customerContacts);
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.customerId = customerId;
        this.companyName = companyName;
    }


    @Override
    public List<ICustomerContact> getContacts() {
        return customerContacts;
    }

    @Override
    public IAddress getBillingAddress() {
        return billingAddress;
    }

    @Override
    public IAddress getShippingAddress() {
        return shippingAddress;
    }

    @Override
    public int getCustomerID() {
        return customerId;
    }

    @Override
    public int getCompanyOrgNumber() {
        return companyOrgNumber;
    }

    @Override
    public String getCompanyName() {
        return companyName;
    }

    @Override
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public void addCustomerContact(ICustomerContact customerContact) {
        customerContacts.add(customerContact);
    }

    @Override
    public void removeCustomerContact(ICustomerContact customerContact) {
        customerContacts.remove(customerContact);
    }

    @Override
    public void setBillingAddress(IAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override
    public void setShippingAddress(IAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public void setCompanyOrgNumber(int companyOrgNumber) {
        this.companyOrgNumber = companyOrgNumber;
    }
}
