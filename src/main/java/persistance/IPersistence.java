package persistance;

import model.site.Site;
import model.User;
import model.article.Article;
import model.customer.Customer;
import model.order.Order;

import java.util.List;

public interface IPersistence {
    void save(Customer customer);
    void save(Article article);
    void save(Site site);
    void save(User user);
    void save(Order order);

    void update(Customer customer);
    void update(Article article);
    void update(Site site);
    void update(User user);
    void update(Order order);

    List<Customer> loadAllCustomers();
    List<Article> loadAllArticles();
    List<Site> loadAllSites();
    List<User> loadAllUsers();
    List<Article> loadAllOrders();

    Customer findOneCustomer(int customerId);
    Article findOneArticle(int articleId);
    Site findOneSite(int siteId);
    User findOneUser(int userId);
    Order findOneOrder(int orderId);

}
