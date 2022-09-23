package model.customer;

public class CustomerContact implements ICustomerContact{

    private String contactPerson;
    private String phoneNumber;
    private String email;

    public CustomerContact(String contactPerson, String phoneNumber, String email) {
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    @Override
    public String getContactPerson() {
        return contactPerson;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
