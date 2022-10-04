package model.order;


import model.User;
import model.article.Article;
import model.customer.Customer;
import model.site.Site;

import java.util.GregorianCalendar;
import java.util.List;


public class Order {
    private User user;
    private int orderNumber;
    private Customer customer;
    private OrderStatus orderStatus;
    private boolean priority;
    private GregorianCalendar orderDate;
    private GregorianCalendar deadline;
    private List<OrderRow> orderRows;
    private Site site;

    public static int CURRENTORDER = 0; //senare "CurrentOrderNumber = orderList.size + 1;"

    public Order(User user, int orderNumber, Customer customer, OrderStatus orderStatus, boolean priority, GregorianCalendar orderDate, GregorianCalendar deadline, List<OrderRow> orderRows, Site site) {
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


}
