package persistance.pojos;

import model.customer.Address;
import model.customer.CustomerContact;

public class CustomerJSON {
    private long customerId;
    private String companyName;
    private int companyOrgNumber;
    private Address billingAddress;
    private Address shippingAddress;
    private CustomerContact[] customerContacts;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyOrgNumber() {
        return companyOrgNumber;
    }

    public void setCompanyOrgNumber(int companyOrgNumber) {
        this.companyOrgNumber = companyOrgNumber;
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

    public CustomerContact[] getCustomerContacts() {
        return customerContacts;
    }

    public void setCustomerContacts(CustomerContact[] customerContacts) {
        this.customerContacts = customerContacts;
    }
}
