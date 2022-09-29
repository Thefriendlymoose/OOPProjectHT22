package model.customer;

import java.util.List;

public interface ICustomerBuilder {

    void startBuild();
    void setCompanyName(String name);
    void setCompanyOrgNumber(int orgNumber);
    void setBillingAddress(IAddress address);
    void setShippingAddress(IAddress address);
    void addCustomerContacts(List<ICustomerContact> contacts);
    void addCustomerContacts(ICustomerContact contact);

    ICustomer buildCustomer();
}
