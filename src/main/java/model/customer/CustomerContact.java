package model.customer;

/**
 * Responsibility: storing contact information
 * Used by: Customer
 * Uses: String
 * @author Simon Porsgaard / doktorjevksy
 */

public class CustomerContact {

    private String contactPerson;
    private String phoneNumber;
    private String email;

    public CustomerContact(String contactPerson, String phoneNumber, String email) {
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public CustomerContact(){};

    // getters
    public String getContactPerson() {
        return contactPerson;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    // setters
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        String name;
        String nr;
        String em;

        if (contactPerson == null){
            name = "";
        } else {
            name = contactPerson;
        }

        if (phoneNumber == null){
            nr = "";
        } else {
            nr = phoneNumber;
        }

        if (email == null){
            em = "";
        } else {
            em = email;
        }
        return name +
                "  " + nr +
                "  " + em;
    }
}
