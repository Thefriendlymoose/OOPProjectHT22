package model;


import java.util.Calendar;
import java.util.List;

public class Order {
    private int userId;
    private int orderNumber;
    private int customerId;
    private OrderStatus orderStatus;
    private boolean priority;
    private Calendar orderDate;
    private Calendar deadline;
    private List<Article> articles;

}
