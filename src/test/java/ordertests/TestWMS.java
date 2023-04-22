package ordertests;

import model.WMS;
import model.article.Article;
import model.article.ArticleCategory;
import model.article.ArticleStatus;
import model.article.Articles;
import model.authentication.UserAuthentication;
import model.customer.Customer;
import model.customer.CustomerModel;
import model.finance.financeModel.FinanceModel;
import model.order.Order;
import model.order.OrderRow;
import model.order.OrderStatus;
import model.order.Orders;
import model.site.Site;
import model.site.SiteArticle;
import model.site.Sites;
import model.user.Role;
import model.user.User;
import model.user.Users;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestWMS {
    private WMS wms;

    public TestWMS(){
        Users users = new Users(new HashMap<>());
        User user = new User(users.getNextUserID(), "testuser", "1234", "test name", true, Role.getAdmin());
        users.addUser(user);

        Articles articles = new Articles(new HashMap<>());
        Article art = new Article(articles.getNextId(), "test name", "test description", ArticleCategory.Office, ArticleStatus.Active, 25, 50, user, LocalDateTime.now(), LocalDateTime.now());
        articles.addArticle(art);
        Article art1 = new Article(articles.getNextId(), "test name", "test description", ArticleCategory.Beds, ArticleStatus.Active, 25, 50, user, LocalDateTime.now(), LocalDateTime.now());
        articles.addArticle(art1);
        Article art2 = new Article(articles.getNextId(), "test name", "test description", ArticleCategory.Electronics, ArticleStatus.Active, 25, 50, user, LocalDateTime.now(), LocalDateTime.now());
        articles.addArticle(art2);
        Article art3 = new Article(articles.getNextId(), "test name", "test description", ArticleCategory.Kitchen, ArticleStatus.Active, 25, 50, user, LocalDateTime.now(), LocalDateTime.now());
        articles.addArticle(art3);
        Article art4 = new Article(articles.getNextId(), "test name", "test description", ArticleCategory.Kitchen, ArticleStatus.Limited, 25, 50, user, LocalDateTime.now(), LocalDateTime.now());
        articles.addArticle(art4);



        CustomerModel customers = new CustomerModel(new HashMap<>());
        Customer cust1 = new Customer(new ArrayList<>(), null, null, customers.getNextId(), 24, "testName");
        customers.saveCustomer(cust1);


        Sites sites = new Sites(new HashMap<>());
        List<SiteArticle> sa = new ArrayList<>();
        sa.add(new SiteArticle(art, 25));
        List<User> emp = new ArrayList<>();
        emp.add(user);

        Site site = new Site(sites.getNextId(), "test site", "test site address", 500, sa, emp);

        sites.addSite(site);

        Orders orders = new Orders(new HashMap<>());

        List<OrderRow> rows1 = new ArrayList<>();
        List<OrderRow> rows2 = new ArrayList<>();
        rows1.add(new OrderRow(art, 1));
        rows2.add(new OrderRow(art, 1));

        Order order1 = new Order(user, orders.getNextOrderNumber(), customers.getCustomerById(1L), OrderStatus.ACTIVE, true, LocalDateTime.now(), LocalDateTime.now(), rows1, site);
        orders.addOrder(order1);

        Order order2 = new Order(user, orders.getNextOrderNumber(), customers.getCustomerById(1L), OrderStatus.ACTIVE, true, LocalDateTime.now(), LocalDateTime.now(), rows2, site);
        orders.addOrder(order2);

        Order order3 = new Order(user, 1000, customers.getCustomerById(1L), OrderStatus.FINISHED, true, LocalDateTime.now(), LocalDateTime.now(), rows2, site);
        orders.addOrder(order3);

        UserAuthentication ua = new UserAuthentication();

        this.wms = new WMS(articles, orders, sites, customers, users, ua, new FinanceModel(new HashMap<>()));
    }

    public WMS getWMS(){
        return wms;
    }
}
