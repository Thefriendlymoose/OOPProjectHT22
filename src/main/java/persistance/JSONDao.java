package persistance;

import com.google.gson.Gson;
import model.site.Site;
import model.User;
import model.article.Article;
import model.customer.Customer;
import model.order.Order;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

// TODO find a way to simplify the interface
//  Using the DAO Pattern (Data Access Object)


public class JSONDao implements IPersistance{

    private Gson gson = new Gson();

    @Override
    public void save(Customer customer) {

    }

    @Override
    public void save(Article article) {
        String json = gson.toJson(article);

        System.out.println(json);

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
        try {
            Reader reader = Files.newBufferedReader(Path.of("src/main/resources/article.json"));

            System.out.println("yo");

            return Arrays.asList(gson.fromJson(reader, Article[].class));

        } catch (Exception e){
            System.out.println(e);
            return null;
        }


    }

    @Override
    public List<Site> loadAllSites() {
        try {
            Reader reader = Files.newBufferedReader(Path.of("src/main/resources/sites.json"));

            return Arrays.asList(gson.fromJson(reader, Site[].class));

        } catch (Exception e){
            System.out.println(e);
            return null;
        }

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
        List<Article> arts = loadAllArticles();

        for (Article art : arts){
            if (art.getArticleId() == articleId){
                return art;
            }
        }

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
