package persistence.dataaccessobjects;

//import model.Order;

import com.google.gson.*;
import model.user.User;
import model.article.Article;
import model.customer.Customer;
import model.order.Order;
import model.order.OrderRow;
import model.site.Site;
import persistence.IPersistence;
import persistence.SerializeBuilder;
import persistence.WriterHelper;
import persistence.pojos.OrderJSON;
import persistence.pojos.OrderRowJSON;

import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Data Access Object which handles loading/saving order data from/to JSON
 */
public final class OrderDAO implements IPersistence<Order> {

    private static OrderDAO instance;
    private final String file = "src/main/resources/order.json";

    private final Map<Long, Order> orders;

    private OrderDAO(){

        this.orders = new HashMap<>();

        Map<Long, User> users = UserDAO.getInstance().getAllMap();
        Map<Long, Article> articles = ArticlesDAO.getInstance().getAllMap();
        Map<Long, Customer> customer = CustomersDAO.getInstance().getAllMap();
        Map<Long, Site> sites = SitesDAO.getInstance().getAllMap();

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
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
            reader.close();
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

    /**
     * Serializes and saves a list of orders into a JSON file
     * @param list
     */
    @Override
    public void save(List<Order> list) {
        SerializeBuilder sb = new SerializeBuilder();
        sb.addUserSerializer();
        sb.addLocalDateTimeSerializer();
        sb.addCustomerSerializer();
        sb.addArticleSerializer();
        sb.addSiteSerializer();
        WriterHelper<Order> wh = new WriterHelper<>();
        wh.writeToFileSerializer(file, list, sb.getGson());
    }

    @Override
    public Map<Long, Order> getAllMap() {
        return orders;
    }



}
