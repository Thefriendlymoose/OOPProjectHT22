package model.customer;

import model.observer.Observable;
import model.observer.Observer;
import persistence.IPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Responsibility: creating a facade between the data and the controllers
 * Used by: CustomerEditor, CustomerEditController, CustomerMenuController
 * Uses: Customer, Observer, IPersistence, List, CustomerEditor
 * @author Simon Porsgaard / doktorjevksy
 */

public class CustomerModel implements Observable {

    private Map<Long, Customer> customers;
    private List<Observer> observers = new ArrayList<>();
    private Long nextId;

    public CustomerModel(Map<Long, Customer> customers){
        this.customers = customers;
        if (customers.isEmpty()){
            nextId = 1L;
        } else {
            nextId = Collections.max(customers.keySet()) + 1;
        }
    }

    /**
     * The newCustomer method returns an editor with an empty Customer
     * @return CustomerEditor
     */

    public CustomerEditor newCustomer(){ return new CustomerEditor(new Customer(nextId), this); }

    /**
     *
     * @param c : the customer to be edited
     * @return CustomerEditor with the customer to be edited
     */

    public CustomerEditor editCustomer(Customer c){ return new CustomerEditor(c, this);}

    public void saveCustomer(Customer c){
        customers.put(c.getCustomerID(), c);
        nextId = Collections.max(customers.keySet()) + 1;
        notifyObservers();
    }

    public void removeCustomer(Customer c){
        customers.remove(c.getCustomerID());
        notifyObservers();
    }

    public Customer getCustomerById(Long id){
        return customers.get(id);
    }

    public List<Customer> getCustomerList(){
        return new ArrayList<>(customers.values());
    }

    public Long getNextId() {
        return nextId;
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
