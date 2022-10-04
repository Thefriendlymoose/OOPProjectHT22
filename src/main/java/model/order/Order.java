package model.order;


import model.article.Article;
import model.site.Site;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class Order {
    private int userId;
    private int orderNumber;
    private int customerId;
    private OrderStatus orderStatus;
    private boolean priority;
    private GregorianCalendar orderDate;
    private GregorianCalendar deadline;
    private List<Article> articles;

    private Site site;

    public static int CURRENTORDER = 0; //senare "CurrentOrderNumber = orderList.size + 1;"

    public Order(int userId, int orderNumber, int customerId, OrderStatus orderStatus, boolean priority, GregorianCalendar orderDate, GregorianCalendar deadline, List<Article> articles) {
        this.userId = userId;
        this.orderNumber = orderNumber;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.priority = priority;
        this.orderDate = orderDate;
        this.deadline = deadline;
        this.articles = articles;
    }

    public int getUserId() {
        return userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return userId == order.userId && orderNumber == order.orderNumber && customerId == order.customerId && priority == order.priority && orderStatus == order.orderStatus && Objects.equals(orderDate, order.orderDate) && Objects.equals(deadline, order.deadline) && Objects.equals(articles, order.articles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, orderNumber, customerId, orderStatus, priority, orderDate, deadline, articles);
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", orderNumber=" + orderNumber +
                ", customerId=" + customerId +
                ", orderStatus=" + orderStatus +
                ", priority=" + priority +
                ", orderDate=" + orderDate.get(Calendar.YEAR) + "-" + orderDate.get(Calendar.MONTH) + "-" + orderDate.get(Calendar.DATE)  +
                ", deadline=" + deadline.get(Calendar.YEAR) + "-" + deadline.get(Calendar.MONTH) + "-" + deadline.get(Calendar.DATE)  +
                ", articles=" + articles +
                '}';
    }
}
