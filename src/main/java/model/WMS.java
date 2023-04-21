package model;

import model.article.Article;
import model.authentication.AuthenticationStatus;
import model.authentication.Session;
import model.article.Articles;
import model.authentication.UserAuthentication;
import model.customer.CustomerModel;
import model.finance.financeModel.FinanceModel;
import model.finance.financeModel.SiteFinanceModel;
import model.observer.Observable;
import model.observer.Observer;
import model.order.Order;
import model.order.Orders;
import model.site.Site;
import model.site.Sites;
import model.user.Permission;
import model.user.User;
import model.user.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WMS implements Observable {
    private Articles articles;
    private Orders orders;
    private Sites sites;
    private Users users;
    private FinanceModel financeModel;

    private CustomerModel customerModel;
    private UserAuthentication userAuthentication;

    private List<Observer> observers;

    public WMS(Articles articles, Orders orders, Sites sites, CustomerModel customerModel, Users users, UserAuthentication ua) {
        this.articles = articles;
        this.orders = orders;
        this.sites = sites;
        this.customerModel = customerModel;
        this.users = users;
        this.userAuthentication = ua;
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

    public UserAuthentication getUserAuthentication() {
        return userAuthentication;
    }

    public Session getSession() {
        return userAuthentication.getSession();
    }

    public AuthenticationStatus login(String user, String password){
        return userAuthentication.logIn(user, password, users);
    }

    public Map<Long, SiteFinanceModel> getFinanceModels() throws Exception {
        if (!getSession().hasAccess(Permission.FINANCE) || getSession().hasAccess(Permission.ALL)) {
            throw new Exception("Permission denied");
        }
        User user = getSession().getUser();
        Map<Long, SiteFinanceModel> out = new HashMap<>();
        sites
                .getInList()
                .stream()
                .filter(s -> s.getSiteUsers().contains(user) || user.getRole().hasPermission(Permission.ALL))
                .map(Site::getSiteId)
                .toList()
                .forEach(siteId -> {
                    try {
                        out.put(siteId, financeModel.getSiteFinanceModel(siteId));
                    } catch (Exception ignore) {}
                });

        return out;
    }

    public void addNewSiteFinanceModel(long id) throws Exception {
        if (!getSession().hasAccess(Permission.FINANCE) || !getSession().hasAccess(Permission.ALL)){
            throw new Exception("Permission denied");
        }
        else if (!sites.getById(id).getSiteUsers().contains(getSession().getUser())) {
            throw new Exception("Permission denied");
        } else {
            financeModel.addNewSiteFinanceModel(id);
            notifyObservers();
        }

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
