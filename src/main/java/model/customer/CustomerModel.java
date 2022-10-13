package model.customer;

import model.observer.Observable;
import model.observer.Observer;
import persistence.CustomersDAO;
import persistence.IPersistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerModel implements Observable {


    private IPersistence<Customer> dao;
    private Map<Long, Customer> customers;
    private List<Observer> observers = new ArrayList<>();

    public CustomerModel(IPersistence<Customer> dao){
        this.dao = dao;
        this.customers = dao.getAllMap();
    }

    public CustomerEditor newCustomer(){

        return new CustomerEditor(new Customer(dao.getNextId()), this);
    }

    public CustomerEditor editCustomer(Customer c){
        return new CustomerEditor(c, this);
    }

    public void saveCustomer(Customer c){
        dao.save(c);
        customers = dao.getAllMap();
        notifyObservers();
    }

    public Customer getCustomerById(Long id){
        return customers.get(id);
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
