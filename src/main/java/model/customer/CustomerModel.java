package model.customer;

import model.observer.Observable;
import model.observer.Observer;
import persistence.IPersistence;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsibility: creating a facade between the data and the controllers
 * Used by: CustomerEditor, CustomerEditController, CustomerMenuController
 * Uses: Customer, Observer, IPersistence, List, CustomerEditor
 * @author Simon Porsgaard / doktorjevksy
 */

public class CustomerModel implements Observable {


    private IPersistence<Customer> dao;
    private List<Observer> observers = new ArrayList<>();

    public CustomerModel(IPersistence<Customer> dao){
        this.dao = dao;
    }

    /**
     * The newCustomer method returns an editor with an empty Customer
     * @return CustomerEditor
     */

    public CustomerEditor newCustomer(){ return new CustomerEditor(new Customer(dao.getNextId()), this); }

    /**
     *
     * @param c : the customer to be edited
     * @return CustomerEditor with the customer to be edited
     */

    public CustomerEditor editCustomer(Customer c){ return new CustomerEditor(c, this);}

    public void saveCustomer(Customer c){
        dao.save(c);
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
