package controller.persistance;

import model.Site;
import model.User;
import model.article.Article;
import model.customer.Customer;
import model.order.Order;

import java.util.List;

// TODO find a way to simplify the interface
//  Using the DAO Pattern (Data Access Object)


public class JSONDao implements IPersistance{

    @Override
    public void save(Customer customer) {

    }

    @Override
    public void save(Article article) {

    }

    @Override
    public void save(Site site) {

    }

    @Override
    public void save(User user) {

    }

    @Override
    public void save(Order order) {

    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void update(Article article) {

    }

    @Override
    public void update(Site site) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void update(Order order) {

    }

    @Override
    public List<Customer> loadAllCustomers() {
        return null;
    }

    @Override
    public List<Article> loadAllArticles() {
        return null;
    }

    @Override
    public List<Site> loadAllSites() {
        return null;
    }

    @Override
    public List<User> loadAllUsers() {
        return null;
    }

    @Override
    public List<Article> loadAllOrders() {
        return null;
    }

    @Override
    public Customer findOneCustomer(int customerId) {
        return null;
    }

    @Override
    public Article findOneArticle(int articleId) {
        return null;
    }

    @Override
    public Site findOneSite(int siteId) {
        return null;
    }

    @Override
    public User findOneUser(int userId) {
        return null;
    }

    @Override
    public Order findOneOrder(int orderId) {
        return null;
    }
}
