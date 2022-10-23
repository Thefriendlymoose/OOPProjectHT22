package customertests;

import model.customer.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import persistence.dataaccessobjects.CustomersDAO;
import persistence.IPersistence;

import java.util.List;

public class CustomerDAOTEST {

    IPersistence<Customer> dao = CustomersDAO.getInstance();

    @Test
    public void uniqueIDTest(){
        long id = dao.getNextId();
        Customer newCustomer = new Customer(id, 1234);
        dao.save(newCustomer);

        long id2 = dao.getNextId();
        Customer anotherCustomer = new Customer(id2, 4321);
        dao.save(anotherCustomer);

        List<Customer> customers = dao.getAll();
        Assertions.assertTrue(newCustomer.getCustomerID() != anotherCustomer.getCustomerID());
    }
}
