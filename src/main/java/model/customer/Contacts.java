package model.customer;

import model.pojos.ContactsPojo;

public class Contacts implements IContacts{

    private ContactsPojo pojo;

    public Contacts(ContactsPojo contactsPojo){
        pojo = contactsPojo;
    }

    @Override
    public String getContactPerson() {
        return pojo.getContactName();
    }

    @Override
    public String getPhoneNumber() {
        return pojo.getContactPhone();
    }

    @Override
    public String getEmail() {
        return pojo.getContactEmail();
    }
}
