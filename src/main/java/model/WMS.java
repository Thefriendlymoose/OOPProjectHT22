package model;

import model.authentication.Session;
import model.article.Articles;
import model.customer.CustomerModel;
import model.order.Orders;
import model.site.Sites;

public class WMS {
    private Articles articles;
    private Orders orders;
    private Sites sites;
    private CustomerModel customerModel;

    private Session session;

    public WMS(Articles articles, Orders orders, Sites sites, CustomerModel customerModel) {
        this.articles = articles;
        this.orders = orders;
        this.sites = sites;
        this.customerModel = customerModel;
    }

    public Articles getArticles() {
        return articles;
    }

    public Orders getOrders() {
        return orders;
    }

    public Sites getSites() {
        return sites;
    }

    public CustomerModel getCustomerModel(){ return customerModel; }


    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
