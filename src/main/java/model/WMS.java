package model;

import model.article.Articles;
import model.order.Orders;
import model.site.Sites;

public class WMS {
    private Articles articles;
    private Orders orders;
    private Sites sites;

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


}
