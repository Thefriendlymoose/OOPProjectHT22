package model.order;

import model.observer.Observable;
import model.observer.Observer;
import model.site.Site;
import persistence.OrderDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * A facade to order objects, this enumerates every order uniquely and makes orders observers.
 *
 */
public class Orders implements Observable {

    private Map<Long, Order> orders;
    private Long nextOrderNumber;
    private List<Observer> observers;

    public Orders(Map<Long, Order> orders){
        this.orders = orders;
        nextOrderNumber = Collections.max(orders.keySet()) + 1;
        observers = new ArrayList<>();
    }

    /**
     * Returns all orders in the site as an array.
     *
     * @param site is the which has the orders
     * @return all orders in the site as an array.
     */

    public List<Order> getOrdersBySite(Site site){
        List<Order> temp = new ArrayList<>();

        for (Order order : orders.values()){
            if (order.getSite().equals(site)){
                temp.add(order);
            }
        }
        return temp;
    }

    /** all orders as an array.
     *
     * @return all orders as an array.
     */
    public List<Order> getAllOrders(){
        return new ArrayList<>(orders.values());
    }

    /**
     * Returns the sum of all orders given a specific status.
     *
     * @param status is the type of status.
     * @return the sum of all orders with a specific status.
     */
    public float getCostPerOrderStatus(OrderStatus status){
        int sum = 0;
        for (Order order : orders.values()){
            if (order.getOrderStatus().equals(status)){
                sum += order.getTotalCost();
            }
        }
        return sum;
    }

    /**
     * Returns the sum of revenue given a specific status.
     *
     * @param status is the type of status.
     * @return the sum of revenue given specific status
     */
    public float getRevenuePerOrderStatus(OrderStatus status){
        int sum = 0;
        for (Order order : orders.values()){
            if (order.getOrderStatus().equals(status)){
                sum += order.getTotalRevenue();
            }
        }
        return sum;
    }

    /**
     * Returns the sum of profit given a specific status.
     *
     * @param status is the type of status.
     * @return the sum of revenue given a specific status.
     */

    public float getProfitPerOrderStatus(OrderStatus status){
        return getRevenuePerOrderStatus(status) - getCostPerOrderStatus(status);
    }

    /**
     * Verifies if a specific OrderNumber exists in Orders.
     *
     * @param id is the OrderNumber
     * @return true if it exists, else false.
     */
    public boolean checkIfExist(Long id){
        return orders.containsKey(id);
    }

    /**
     * Returns an order in Order with a specified OrderNumber.
     *
     * @param id the OrderNumber.
     * @return an order in Order with a specified OrderNumber
     */
    public Order findById(Long id){
        return orders.get(id);
    }

    public List<Order> getInList() {
        return new ArrayList<>(orders.values());
    }

    public Order getByOrderNumber(Long orderNumber){
        return orders.get(orderNumber);
    }

    public void addOrder(Order order){
        orders.put(order.getOrderNumber(), order);
        nextOrderNumber++;
        notifyObservers();
    }

    public void removeOrder(Order order){
        orders.remove(order.getOrderNumber());
        notifyObservers(); }

    public Long getNextOrderNumber(){
        return nextOrderNumber;
    }

    public void updateOrder(){
        notifyObservers();
    }


    @Override
    public void registerObserver(Observer o) {observers.add(o);

    }

    @Override
    public void unregisterObserver(Observer o) { observers.remove(o);

    }

    @Override
    public void unregisterAll() { observers = new ArrayList<>();

    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers){
            o.update();
        }
    }


//    temporary
    @Override
    public String toString() {
        return "Orders{" +
                "orders=" + orders +
                ", nextOrderNumber=" + nextOrderNumber +
                ", observers=" + observers +
                '}';
    }
}
