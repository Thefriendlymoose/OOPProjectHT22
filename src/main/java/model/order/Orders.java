package model.order;

import model.customer.Customer;
import model.observer.Observer;
import model.site.Site;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * A facade to order objects (Facade pattern), this enumerates every order uniquely
 * and makes orders observables (Observer pattern).
 *
 * @author James Pålsson
 * @author David al Amiri
 */
public class Orders {

    private Map<Long, Order> orders;
    private Long nextOrderNumber;

    public Orders(Map<Long, Order> orders){
        this.orders = orders;
        if (orders.isEmpty()){
            nextOrderNumber = 1L;
        } else {
            nextOrderNumber = Collections.max(orders.keySet()) + 1;
        }
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

    public List<Order> getOrdersByCustomer(Customer customer) {
        List<Order> os = new ArrayList<>();
        for (Order o : orders.values()){
            if (o.getCustomer().equals(customer)){
                os.add(o);
            }
        }
        return os;
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

    /**
     * Returns a list of all order objects in Orders.
     *
     * @return a list of all order objects in Orders.
     */

    public List<Order> getInList() {
        return new ArrayList<>(orders.values());
    }

    /**
     * Adds an order object to orders with a unique OrderNumber.
     *
     * @param order is the order which is added.
     */
    public void addOrder(Order order){
        orders.put(order.getOrderNumber(), order);
        nextOrderNumber = Collections.max(orders.keySet()) + 1;
    }

    /**
     * Returns the unique next OrderNumber.
     *
     * @return the unique next OrderNUmber.
     */
    public Long getNextOrderNumber(){
        return nextOrderNumber;
    }


    /**
     * Returns a Boolean array of {true, false}.
     *
     * @return a Boolean array of {true, false}.
     */
    public Boolean[] getAllPriorities() {
        return new Boolean[]{true,false};
    }

}
