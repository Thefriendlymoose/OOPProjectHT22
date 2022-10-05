package model.customer;

import persistence.CustomersDAO;
import persistence.IPersistence;

public class CustomerBuilder {

    private IPersistence<Customer> dao = CustomersDAO.getInstance();
    private Customer customer;

    public CustomerBuilder(){
        customer = new Customer(dao.getNextId());
    }

    public void setCompanyName(String name){
        customer.setCompanyName(name);
    }




}
