package persistence.pojos;

import model.order.OrderStatus;

import java.util.GregorianCalendar;
import java.util.List;

public class OrderJSON {
    private long orderNumber;
    private long user;
    private long customer;
    private OrderStatus orderStatus;
    private boolean priority;
    private GregorianCalendar orderDate;
    private GregorianCalendar deadline;
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

    public GregorianCalendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(GregorianCalendar orderDate) {
        this.orderDate = orderDate;
    }

    public GregorianCalendar getDeadline() {
        return deadline;
    }

    public void setDeadline(GregorianCalendar deadline) {
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
