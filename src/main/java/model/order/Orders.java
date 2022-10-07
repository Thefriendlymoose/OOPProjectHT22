package model.order;

import model.site.Site;
import persistence.OrderDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Orders {

    private Map<Long, Order> orders;


    public Orders(){
        orders = OrderDAO.getInstance().getAllMap();
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
}
