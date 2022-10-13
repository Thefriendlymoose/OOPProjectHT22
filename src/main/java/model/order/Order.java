package model.order;


import model.user.User;
import model.customer.Customer;
import model.site.Site;

import java.time.LocalDateTime;
import java.util.List;


public class Order {
    private User user;
    private long orderNumber;
    private Customer customer;
    private OrderStatus orderStatus;
    private boolean priority;
    private LocalDateTime orderDate;
    private LocalDateTime deadline;
    private List<OrderRow> orderRows;
    private Site site;


    public Order(User user, long orderNumber, Customer customer, OrderStatus orderStatus, boolean priority, LocalDateTime orderDate, LocalDateTime deadline, List<OrderRow> orderRows, Site site) {
        this.user = user;
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.orderStatus = orderStatus;
        this.priority = priority;
        this.orderDate = orderDate;
        this.deadline = deadline;
        this.orderRows = orderRows;
        this.site = site;
    }

    public User getUser() {
        return user;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public boolean isPriority() {
        return priority;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public List<OrderRow> getOrderRows() {
        return orderRows;
    }

    public Site getSite() {
        return site;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setOrderRows(List<OrderRow> orderRows) {
        this.orderRows = orderRows;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public float getTotalCost(){
        float sum = 0;
        for (OrderRow or : orderRows){
            sum += or.getArticle().getCost() * or.getAmount();
        }
        return sum;
    }

    public float getTotalRevenue(){
        float sum = 0;
        for (OrderRow or : orderRows){
            sum += or.getArticle().getSellPrice() * or.getAmount();
        }
        return sum;
    }

    public int getTotalAmount(){
        int sum = 0;
        for (OrderRow or : orderRows){
            sum += or.getAmount();
        }
        return sum;
    }

}
