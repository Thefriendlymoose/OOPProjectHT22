package persistance;

import com.google.gson.Gson;
import model.site.Site;
import model.User;
import model.article.Article;
import model.customer.Customer;
import model.order.Order;
import model.site.SiteArticle;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

// TODO find a way to simplify the interface
//  Using the DAO Pattern (Data Access Object)


public class JSONDao implements IPersistence {

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
            /* Läs in datafiler */
            Reader readerSites = Files.newBufferedReader(Path.of("src/main/resources/sites.json"));
            Reader readerSiteArticles = Files.newBufferedReader(Path.of("src/main/resources/sitearticles.json"));
            Reader readerSiteUsers = Files.newBufferedReader(Path.of("src/main/resources/siteusers.json"));
            Reader readerUsers = Files.newBufferedReader(Path.of("src/main/resources/users.json"));

            /* Skapa listor av inläst data */
            List<SiteJSON> siteJSONS = Arrays.asList(gson.fromJson(readerSites, SiteJSON[].class));
            List<SiteArticleJSON> siteArticleJSONS = Arrays.asList(gson.fromJson(readerSiteArticles, SiteArticleJSON[].class));
            List<SiteUserJSON> siteUserJSONS = Arrays.asList(gson.fromJson(readerSiteUsers, SiteUserJSON[].class));
            List<UserJSON> usersJSONS = Arrays.asList(gson.fromJson(readerUsers, UserJSON[].class));
            /* Skapa hashtabeller, vore kanske smartare om dessa fanns som privata attribut i DAO klassen och lästes in
            en gång när den instansieras istället för att öppna json-filer varje gång
             */
            HashMap<Integer, Site> sites= new HashMap<>();
            HashMap<Integer, User> users= new HashMap<>();
            HashMap<Integer, List<SiteArticle>> siteArticlesBySite = new HashMap<>();
            HashMap<Integer, List<User>> usersBySite = new HashMap<>();

            /* Skapa och sortera upp applikationens objekt från json-indata */
            for(UserJSON uj : usersJSONS) {
                User user=new User(uj.getUserId(), uj.getUserName(), uj.getPassword(), uj.getName(),
                        uj.isStatus(), uj.getPermissions());
                users.put(user.getUserId(), user);
            }

            for(SiteArticleJSON sa : siteArticleJSONS){
                SiteArticle siteArticle = new SiteArticle(sa.getSiteArticleId(), sa.getArticleId(), sa.getSite(),
                        sa.getAmount());
                if(!siteArticlesBySite.containsKey(sa.getSite())) {
                    List<SiteArticle> saList = new ArrayList<>();
                    saList.add(siteArticle);
                    siteArticlesBySite.put(siteArticle.getSite(), saList);
                }
                else {
                    siteArticlesBySite.get(siteArticle.getSite()).add(siteArticle);
                }
            }

            for(SiteJSON sj : siteJSONS) {
                ArrayList<User> susers = new ArrayList<>();
                Site site = new Site(sj.getSiteId(), sj.getSiteName(), sj.getSiteAddress(), sj.getMaxCapacity(),
                        siteArticlesBySite.get(sj.getSiteId()), susers);
               for(SiteUserJSON suj : siteUserJSONS) {
                   if(suj.getSiteId() == site.getSiteId()) {
                       site.addSiteEmployee(users.get(suj.getUserId()));
                   }
               }
               sites.put(site.getSiteId(), site);
            }

            // return Arrays.asList(gson.fromJson(reader, Site[].class));
            return sites.values().stream().collect(Collectors.toList());

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
