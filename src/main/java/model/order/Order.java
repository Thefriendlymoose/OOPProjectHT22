package model.order;


import model.User;
import model.article.Article;
import model.customer.Customer;
import model.site.Site;

import java.time.LocalDateTime;
import java.util.GregorianCalendar;
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

    public static int CURRENTORDER = 0; //senare "CurrentOrderNumber = orderList.size + 1;"

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

}
