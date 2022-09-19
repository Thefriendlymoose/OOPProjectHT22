package model.customer;

public class Contacts implements IContacts{

    private String contactPerson;
    private String phoneNumber;
    private String email;

    public Contacts(String contactPerson, String phoneNumber, String email){
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
}
