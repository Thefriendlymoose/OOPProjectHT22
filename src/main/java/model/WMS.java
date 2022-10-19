package model;

import model.article.Article;
import model.authentication.Session;
import model.article.Articles;
import model.customer.CustomerModel;
import model.observer.Observable;
import model.observer.Observer;
import model.order.Order;
import model.order.Orders;
import model.site.Site;
import model.site.Sites;
import model.user.Users;

import java.util.ArrayList;
import java.util.List;

public class WMS implements Observable {
    private Articles articles;
    private Orders orders;
    private Sites sites;
    private Users users;

    private CustomerModel customerModel;
    private Session session;

    private List<Observer> observers;

    public WMS(Articles articles, Orders orders, Sites sites, CustomerModel customerModel, Users users) {
        this.articles = articles;
        this.orders = orders;
        this.sites = sites;
        this.customerModel = customerModel;
        this.observers = new ArrayList<>();
        this.users = users;
    }

    public Articles getArticles() {
        return articles;
    }

    public void addArticle(Article article) {
        articles.addArticle(article);
        notifyObservers();
    }

    public void updateArticle(){
        articles.updateArticle();
        notifyObservers();
    }


    public Orders getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.addOrder(order);
        notifyObservers();
    }

    public void updateOrder() {
        notifyObservers();
    }

    public Sites getSites() {
        return sites;
    }

    public void addSite(Site site) {
        sites.addSite(site);
        notifyObservers();
    }

    public void updateSite() {
        notifyObservers();
    }

    public Users getUsers() {
        return users;
    }
    public CustomerModel getCustomerModel(){ return customerModel; }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void unregisterAll() {
        observers.clear();
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }
}
