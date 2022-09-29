package model.orderV2;


import model.customer.Customer;
import model.order.OrderStatus;
import model.User;
import model.site.Site;

import java.util.Calendar;
import java.util.List;

public class OrderV2 {

    private int orderNumber;

    private User user;
    private Customer customer;
    private Site site;
    private OrderStatus orderStatus;
    private boolean priority;
    private Calendar orderDate;
    private Calendar deadline;

    private List<OrderRow> cart;





}
