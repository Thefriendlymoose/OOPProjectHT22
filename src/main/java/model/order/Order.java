package model.order;


import model.article.Article;
import model.site.SiteArticle;
import model.user.User;
import model.customer.Customer;
import model.site.Site;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The Order class consists of all other classes that the WMS application is built around i.e., User, Customer,
 * Site and Article (found in OrderRows).
 *
 * @author James Pålsson
 * @author David al Amarai
 */

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

    /**
     * Constructs an Order object.
     *
     */
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

    /**
     * Returns the User object.
     *
     * @return Returns the User object of the Order.
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the long OrderNumber.
     *
     * @return long OrderNumber.
     */
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

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    /**
     * Returns the sum of all article costs in the Order.
     *
     * @return sum of all article costs in the Order.
     */

    public float getTotalCost(){
        float sum = 0;
        for (OrderRow or : orderRows){
            sum += or.getArticle().getCost() * or.getAmount();
        }
        return sum;
    }

    /**
     * Returns the sum of all article sell price in the order.
     *
     * @return sum of all article sell price in the order.
     */
    public float getTotalRevenue(){
        float sum = 0;
        for (OrderRow or : orderRows){
            sum += or.getArticle().getSellPrice() * or.getAmount();
        }
        return sum;
    }

    /**
     * Returns the sum of the number of articles in the order.
     *
     * @return sum of the number of articles in the order.
     */
    public int getTotalAmount(){
        int sum = 0;
        for (OrderRow or : orderRows){
            sum += or.getAmount();
        }
        return sum;
    }

    /**
     * Checks if an article is in a OrderRow.
     *
     * @param article is the article which is being searched for
     * @return true if it exists, otherwise false
     */

    private boolean checkIfOrderRowExist(Article article) {
        for (OrderRow row : orderRows){
            if (row.getArticle() == article) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds article and an amount of that article to an OrderRow.
     *
     * @param article to be added
     * @param amount number of the article which is added
     */

    private void addToOrderRow(Article article, int amount) {
        for (OrderRow row : orderRows) {
            if (row.getArticle() == article) {
                row.addAmount(amount);
            }
        }
    }

    /**
     * Adds Site article and an amount of that site article to an OrderRow,
     * if possible.
     *
     * @param sa is Site Article to be added
     * @param amount number of the Site article sa which is added
     * @return true if possible to add, other false
     */

    public boolean addOrderRow(SiteArticle sa, int amount){
        if (sa.checkIfEnough(amount)){
            if (checkIfOrderRowExist(sa.getArticle())){
                addToOrderRow(sa.getArticle(), amount);
            } else {
                orderRows.add(new OrderRow(sa.getArticle(), amount));
            }
            sa.decreaseAmount(amount);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reduces the amount of an Article in an Order Row, if possible.
     *
     * @param or is the Order Row to be reduced
     * @param amount number of the  sa which is added
     * @return true if possible to reduce, otherwise false
     */

    public boolean reduceOrderRow(OrderRow or, int amount) {
        Article art = or.getArticle();
        if (or.getAmount() == amount){
            orderRows.remove(or);
            return site.addSiteArticle(art, amount);
        } else if (or.getAmount() < amount || amount < 0){
            return false;
        } else if (site.checkIfOverCapacity(amount)) {
            return false;
        } else {
            or.reduceAmount(amount);
            return site.addSiteArticle(art, amount);
        }

    }

}
