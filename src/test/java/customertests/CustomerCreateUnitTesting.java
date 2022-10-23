package customertests;


import model.customer.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import persistence.dataaccessobjects.CustomersDAO;

import java.util.List;

public class CustomerCreateUnitTesting {

    private CustomersDAO dao;
    private CustomerModel model;

    public CustomerCreateUnitTesting(){
        dao = CustomersDAO.getInstance();
        dao.setCustomersFile("C:\\javadev\\OOPProjectHT22\\src\\test\\java\\customerTests\\dummyJsons\\customerTestData.json");
        model = new CustomerModel(dao);
    }

    @Test
    public void createCustomerTest(){
        // this is the id that the customer should have
        long nextFreeID = dao.getNextId();
        CustomerEditor ed = model.newCustomer();
        // the customer is not in the model yet
        Assertions.assertFalse(isFound(nextFreeID, model));

        ed.save();
        // the customer is found when saved
        Assertions.assertTrue(isFound(nextFreeID, model));

        model.removeCustomer(model.getCustomerById(nextFreeID));

        // the customer is then removed
        Assertions.assertFalse(isFound(nextFreeID, model));

    }

    @Test
    public void contactTest(){
        CustomerEditor ed = model.newCustomer();
        CustomerContact contact = new CustomerContact("hey", "112", "a@b.c");
        ed.addContact(contact);
        List<CustomerContact> cs = ed.getContacts();

        // the contact is added
        Assertions.assertTrue(cs.contains(contact));

        ed.removeContact(contact);
        // the contact is removed
        Assertions.assertTrue(!cs.contains(contact));

    }

    @Test
    public void addressTest(){
        CustomerEditor ed = model.newCustomer();
        Address a1 = new Address();
        Address a2 = new Address();
        ed.setBillingStrategy();
        ed.setAddress(a1);
        ed.setShippingStrategy();
        ed.setAddress(a2);
        Customer c = ed.getCustomer();

        // addresses are added
        Assertions.assertTrue(c.getBillingAddress() == a1);
        Assertions.assertTrue(c.getShippingAddress() == a2);
    }

    private boolean isFound(long id, CustomerModel model){
        Customer c = model.getCustomerById(id);
        return c != null;
    }
}
