package persistence;

//import model.Order;

import com.google.gson.*;
import model.User;
import model.article.Article;
import model.customer.Customer;
import model.order.Order;
import model.order.OrderRow;
import model.site.Site;
import persistence.pojos.OrderJSON;
import persistence.pojos.OrderRowJSON;

import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

public final class OrderDAO implements IPersistence<Order> {

    private static OrderDAO instance;
    private final String file = "src/main/resources/order.json";

    private Map<Long, Order> orders;

    private Gson gson;
    private long nextFreeId = 0;

    private Map<Long, User> users;
    private Map<Long, Customer> customer;
    private Map<Long, Site> sites;
    private Map<Long, Article> articles;

    private OrderDAO(){

        this.orders = new HashMap<>();

        this.users = UserDAO.getInstance().getAllMap();
        this.articles = ArticlesDAO.getInstance().getAllMap();
        this.customer = CustomersDAO.getInstance().getAllMap();
        this.sites = SitesDAO.getInstance().getAllMap();

        gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

                return LocalDateTime.parse(json.getAsString());
            }
        }).create();

        try {
            Reader reader = Files.newBufferedReader(Path.of(file));
            List<OrderJSON> orderJSONS = Arrays.asList(gson.fromJson(reader, OrderJSON[].class));

            for (OrderJSON oj : orderJSONS){

                List<OrderRow> rows = new ArrayList<>();

                for (OrderRowJSON rj : oj.getOrderRows()){
                    rows.add(new OrderRow(articles.get(rj.getArticle()), rj.getAmount()));
                }

                Order order = new Order(users.get(oj.getUser()), oj.getOrderNumber(), customer.get(oj.getCustomer()),
                        oj.getOrderStatus(), oj.isPriority(), oj.getOrderDate(), oj.getDeadline(), rows, sites.get(oj.getSite()));

                orders.put(order.getOrderNumber(), order);
            }
            if (orders.size() > 0 ){
                nextFreeId = Collections.max(orders.keySet()) + 1;
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static OrderDAO getInstance(){
        if (instance == null){
            instance = new OrderDAO();
        }
        return instance;
    }

    @Override
    public void save(Order o) {

    }

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Map<Long, Order> getAllMap() {
        return orders;
    }

    @Override
    public long getNextId() {
        return this.nextFreeId;
    }

    @Override
    public Order findById(long id) {
        return orders.get(id);
    }


}
