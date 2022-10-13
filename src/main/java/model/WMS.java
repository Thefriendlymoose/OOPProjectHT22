package model;

import model.authentication.Session;
import model.article.Articles;
import model.order.Orders;
import model.site.Sites;

public class WMS {
    private Articles articles;
    private Orders orders;
    private Sites sites;

    private Session session;

    public WMS(Articles articles, Orders orders, Sites sites) {
        this.articles = articles;
        this.orders = orders;
        this.sites = sites;
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


    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
