package model;

import model.article.Article;
import model.authentication.Session;
import model.article.Articles;
import model.customer.CustomerModel;
import model.observer.Observable;
import model.observer.Observer;
import model.order.Orders;
import model.site.Sites;

import java.util.ArrayList;
import java.util.List;

public class WMS implements Observable {
    private Articles articles;
    private Orders orders;
    private Sites sites;
    private CustomerModel customerModel;
    private Session session;

    private List<Observer> observers;

    public WMS(Articles articles, Orders orders, Sites sites, CustomerModel customerModel) {
        this.articles = articles;
        this.orders = orders;
        this.sites = sites;
        this.customerModel = customerModel;
        this.observers = new ArrayList<>();
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
