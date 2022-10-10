package model.customer;

import persistence.CustomersDAO;
import persistence.IPersistence;

import java.util.List;
import java.util.Map;

public class CustomerModel {

    private IPersistence<Customer> dao;
    private Map<Long, Customer> customers;

    public CustomerModel(){
        dao = CustomersDAO.getInstance();
        customers = dao.getAllMap();
    }

}
