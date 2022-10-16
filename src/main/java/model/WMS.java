package model;

import model.authentication.Session;
import model.article.Articles;
import model.order.Orders;
import model.site.Sites;
import model.user.Users;

public class WMS {
    private Articles articles;
    private Orders orders;
    private Sites sites;
    private Users users;

    private Session session;

    public WMS(Articles articles, Orders orders, Sites sites,Users users) {
        this.articles = articles;
        this.orders = orders;
        this.sites = sites;
        this.users = users;
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

    public Users getUsers() {
        return users;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
