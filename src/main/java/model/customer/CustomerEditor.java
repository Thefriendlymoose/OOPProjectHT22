package model.customer;

import model.observer.Observable;
import model.observer.Observer;
import persistence.CustomersDAO;
import persistence.IPersistence;

import java.util.ArrayList;
import java.util.List;

public class CustomerEditor implements Observable {

    private Customer customer;
    private IPersistence<Customer> dao = CustomersDAO.getInstance();
    private AddressStrategy strategy;
    private List<Observer> observers = new ArrayList<>();

    public CustomerEditor(){
        customer = new Customer(dao.getNextId());
        customer.setBillingAddress(new Address());
        customer.setShippingAddress(new Address());
        strategy = new ShippingAddressStrategy(customer);

    }

    public CustomerEditor(Customer customer){
        this.customer = customer;
    }

    public void setBillingStrategy(){
        strategy = new BillingAddressStrategy(customer);
    }

    public void setShippingStrategy(){
        strategy = new ShippingAddressStrategy(customer);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress(){
        return strategy.getAddress();
    }

    public void setAddress(Address address){
        strategy.setAddress(address);
        notifyObservers();
    }

    public void addContact(CustomerContact contact){
        customer.addCustomerContact(contact);
        notifyObservers();
    }

    public void removeContact(CustomerContact contact){
        customer.removeCustomerContact(contact);
        notifyObservers();
    }

    public List<CustomerContact> getContacts(){
        return customer.getContacts();
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
