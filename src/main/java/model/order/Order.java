package model.order;


import model.article.Article;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Order {
    private int userId;
    private int orderNumber;
    private int customerId;
    private OrderStatus orderStatus;
    private boolean priority;
    private Calendar orderDate;
    private Calendar deadline;
    private List<Article> articles;

    public static int CURRENTORDER = 0; //senare "CurrentOrderNumber = orderList.size + 1;"

    public Order(int userId, int orderNumber, int customerId, OrderStatus orderStatus, boolean priority, Calendar orderDate, Calendar deadline, List<Article> articles) {

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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public Calendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
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
                ", orderDate=" + orderDate +
                ", deadline=" + deadline +
                ", articles=" + articles +
                '}';
    }
}
