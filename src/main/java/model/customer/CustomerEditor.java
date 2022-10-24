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
     * Gets the address accosrding to the current AddressStrategy
     * @return the address to get
     */

    public Address getAddress(){
        return strategy.getAddress();
    }

    /**
     * Sets the address according to the current AddressStrategy
     * Notifies observers
     * @param address the address to be set
     */
    public void setAddress(Address address){
        strategy.setAddress(address);
        notifyObservers();
    }

    /**
     * Adds a contact to the Customer's Contact List
     * Notifies Observers
     * @param contact contact to be added
     */
    public void addContact(CustomerContact contact){
        customer.addCustomerContact(contact);
        notifyObservers();
    }

    /**
     * Removes a contact from the Customer's Contact List
     * Notifies Observers
     * @param contact contact to be removed
     */

    public void removeContact(CustomerContact contact){
        customer.removeCustomerContact(contact);
        notifyObservers();
    }

    public List<CustomerContact> getContacts(){
        return customer.getContacts();
    }


    /**
     * Saves all the edits made on the Customer
     * Notifies observers
     */

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
