package persistance;

import model.customer.Address;
import model.customer.CustomerContact;

public class CustomerJSON {
    int customerId;
    String companyName;
    int companyOrgNumber;
    Address billingAddress;
    Address shippingAddress;
    CustomerContact[] customerContacts;
}
