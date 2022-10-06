package model.customer;

import persistence.CustomersDAO;
import persistence.IPersistence;

public class CustomerEditor {

    private Customer customer;
    private IPersistence<Customer> dao = CustomersDAO.getInstance();


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void newCustomer(){
        customer = new Customer(dao.getNextId());
        customer.setBillingAddress(new Address());
        customer.setShippingAddress(new Address());
    }

    public Address getShippingAddress(){ return customer.getShippingAddress(); }
    public Address getBillingAddress(){ return customer.getBillingAddress(); }
}
