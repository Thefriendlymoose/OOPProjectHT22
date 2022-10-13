package model.customer;

import model.observer.Observable;
import model.observer.Observer;
import persistence.CustomersDAO;
import persistence.IPersistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The customer model figures more or less as a facade to the database
 * supports operations for finding, saving, creating and deleting Customers
 */

public class CustomerModel implements Observable {


    private IPersistence<Customer> dao;
  //  private Map<Long, Customer> customers;
    private List<Observer> observers = new ArrayList<>();

    public CustomerModel(IPersistence<Customer> dao){
        this.dao = dao;
       // this.customers = dao.getAllMap();
    }

    public CustomerEditor newCustomer(){ return new CustomerEditor(new Customer(dao.getNextId()), this); }

    public CustomerEditor editCustomer(Customer c){ return new CustomerEditor(c, this);}

    public void saveCustomer(Customer c){
        dao.save(c);
      //  customers = dao.getAllMap();
        notifyObservers();
    }

    public void removeCustomer(Customer c){
        dao.getAllMap().remove(c.getCustomerID());
        notifyObservers();
    }

    public Customer getCustomerById(Long id){
        return dao.getAllMap().get(id);
    }

    public List<Customer> getCustomerList(){
        return dao.getAll();
    }


    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void unregisterAll() {
        observers = new ArrayList<>();
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers){
            o.update();
        }
    }
}
