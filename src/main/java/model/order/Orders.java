package model.order;

import model.observer.Observable;
import model.observer.Observer;
import model.site.Site;
import persistence.OrderDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Orders implements Observable {

    private Map<Long, Order> orders;
    private Long nextOrderNumber;
    private List<Observer> observers;

    public Orders(Map<Long, Order> orders){
        this.orders = orders;
        nextOrderNumber = Collections.max(orders.keySet()) + 1;
        observers = new ArrayList<>();
    }

    public List<Order> getOrdersBySite(Site site){
        List<Order> temp = new ArrayList<>();

        for (Order order : orders.values()){
            if (order.getSite().equals(site)){
                temp.add(order);
            }
        }
        return temp;
    }

    public List<Order> getAllOrders(){
        return new ArrayList<>(orders.values());
    }

    public float getCostPerOrderStatus(OrderStatus status){
        int sum = 0;
        for (Order order : orders.values()){
            if (order.getOrderStatus().equals(status)){
                sum += order.getTotalCost();
            }
        }
        return sum;
    }
    public float getRevenuePerOrderStatus(OrderStatus status){
        int sum = 0;
        for (Order order : orders.values()){
            if (order.getOrderStatus().equals(status)){
                sum += order.getTotalRevenue();
            }
        }
        return sum;
    }

    public float getProfitPerOrderStatus(OrderStatus status){
        return getRevenuePerOrderStatus(status) - getCostPerOrderStatus(status);
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
}
