package customertests;

import model.customer.Customer;
import model.customer.CustomerModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

public class CustomerDAOTEST {

    CustomerModel cm = new CustomerModel(new HashMap<>());

    @Test
    public void uniqueIDTest(){
        long id = cm.getNextId();
        Customer newCustomer = new Customer(id, 1234);
        cm.saveCustomer(newCustomer);

        long id2 = cm.getNextId();
        Customer anotherCustomer = new Customer(id2, 4321);
        cm.saveCustomer(anotherCustomer);

        List<Customer> customers = cm.getCustomerList();
        Assertions.assertTrue(newCustomer.getCustomerID() != anotherCustomer.getCustomerID());
    }
}
