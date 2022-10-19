package customerTests;


import model.customer.Customer;
import model.customer.CustomerEditor;
import model.customer.CustomerModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import persistence.CustomersDAO;
import persistence.IPersistence;

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

    }

    private boolean isFound(long id, CustomerModel model){
        Customer c = model.getCustomerById(id);
        return c != null;
    }
}
