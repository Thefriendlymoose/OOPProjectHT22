package model.customer;

import model.observer.Observable;
import model.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsibility: encapsulating the process of manipulating data in a Customer object
 * Used by: CustomerEditor, CustomerModel, CustomerEditController,
 *          ContactEditController, AddressCreateController, ContactEditCardController
 * Uses: Customer, CustomerModel, AddressStrategy
 * @author Simon Porsgaard / doktorjevksy
 */

public class CustomerEditor implements Observable {

    private Customer customer;
    private CustomerModel model;
    private AddressStrategy strategy;
    private List<Observer> observers = new ArrayList<>();

    public CustomerEditor(Customer customer, CustomerModel model){
        this.model = model;
        this.customer = customer;
    }

    public void setCompanyName(String name){
        customer.setCompanyName(name);
    }

    public void setCompanyOrgNumber(long orgNumber){
        customer.setCompanyOrgNumber(orgNumber);
    }

    /**
     * Changes the behavior of the get/setAddress() methods to setting and getting the billing address
     */
    public void setBillingStrategy(){
        strategy = new BillingAddressStrategy(customer);
    }

    /**
     * Changes the behavior of the get/setAddress() methods to setting and getting the shipping address
     */

    public void setShippingStrategy(){
        strategy = new ShippingAddressStrategy(customer);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     *
     * @return the address according to current strategy
     */

    public Address getAddress(){
        return strategy.getAddress();
    }

    /**
     *
     * @param address sets the address according to current strategy
     */
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


    public void save(){
        model.saveCustomer(customer);
        notifyObservers();
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
