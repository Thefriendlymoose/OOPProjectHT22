package model.order;

import model.site.Site;
import persistence.OrderDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Orders {

    private Map<Long, Order> orders;


    public Orders(Map<Long, Order> orders){
        this.orders = orders;
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

}
