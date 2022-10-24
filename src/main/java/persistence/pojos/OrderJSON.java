package persistence.pojos;

import model.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Order POJO data class used temporarily when deserializing an order from JSON
 */
public class OrderJSON {
    private long orderNumber;
    private long user;
    private long customer;
    private OrderStatus orderStatus;
    private boolean priority;
    private LocalDateTime orderDate;
    private LocalDateTime deadline;
    private List<OrderRowJSON> orderRows;
    private long site;

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public long getCustomer() {
        return customer;
    }

    public void setCustomer(long customer) {
        this.customer = customer;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public List<OrderRowJSON> getOrderRows() {
        return orderRows;
    }

    public void setOrderRows(List<OrderRowJSON> orderRows) {
        this.orderRows = orderRows;
    }

    public long getSite() {
        return site;
    }

    public void setSite(long site) {
        this.site = site;
    }

}
