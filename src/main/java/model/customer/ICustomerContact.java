package model.customer;

public interface ICustomerContact {

    //getters
    String getContactPerson();
    String getPhoneNumber();
    String getEmail();

    //setters
    void setContactPerson(String contactPerson);
    void setPhoneNumber(String phoneNumber);
    void setEmail(String email);
}
