package model.order;

import java.util.List;

public class DBJOrders {
    private List<Order> orders;

    public DBJOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "DBJOrders{" +
                "orders=" + orders +
                '}';
    }
}
