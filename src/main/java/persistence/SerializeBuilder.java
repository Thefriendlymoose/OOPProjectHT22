package persistence;

import com.google.gson.*;
import model.article.Article;
import model.customer.Address;
import model.customer.Customer;
import model.customer.CustomerContact;
import model.order.OrderRow;
import model.site.Site;
import model.site.SiteArticle;
import model.user.User;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class SerializeBuilder {
    private GsonBuilder gb;

    public SerializeBuilder(){
        this.gb = new GsonBuilder();
    }

    public void addUserSerializer(){
        gb.registerTypeAdapter(User.class, new JsonSerializer<User>() {
            @Override
            public JsonElement serialize(User user, Type type, JsonSerializationContext jsonSerializationContext) {
                return new JsonPrimitive(user.getUserId());
            }
        });
    }

    public void addCustomerSerializer(){
        gb.registerTypeAdapter(Customer.class, new JsonSerializer<Customer>() {

            @Override
            public JsonElement serialize(Customer customer, Type type, JsonSerializationContext jsonSerializationContext) {
                return new JsonPrimitive(customer.getCustomerID());
            }
        });
    }

    public void addLocalDateTimeSerializer(){
        gb.registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {

            @Override
            public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
                return new JsonPrimitive(localDateTime.toString());
            }
        });
    }

    public void addSiteSerializer(){
        gb.registerTypeAdapter(Site.class, new JsonSerializer<Site>() {
            @Override
            public JsonElement serialize(Site site, Type type, JsonSerializationContext jsonSerializationContext) {
                return new JsonPrimitive(site.getSiteId());
            }
        });
    }

    public void addArticleSerializer(){
        gb.registerTypeAdapter(Article.class, new JsonSerializer<Article>() {

            @Override
            public JsonElement serialize(Article article, Type type, JsonSerializationContext jsonSerializationContext) {
                return new JsonPrimitive(article.getArticleId());
            }
        });
    }

    public void addSiteArticleSerializer(){
        gb.registerTypeAdapter(SiteArticle.class, new JsonSerializer<SiteArticle>() {
            @Override
            public JsonElement serialize(SiteArticle siteArticle, Type type, JsonSerializationContext jsonSerializationContext) {
                JsonObject jo = new JsonObject();
                jo.add("articleId", new JsonPrimitive(siteArticle.getArticle().getArticleId()));
                jo.add("amount", new JsonPrimitive(siteArticle.getAmount()));
                return jo;
            }
        });
    }

    public void addOrderRowSerializer(){
        gb.registerTypeAdapter(OrderRow.class, new JsonSerializer<OrderRow>() {
            @Override
            public JsonElement serialize(OrderRow orderRow, Type type, JsonSerializationContext jsonSerializationContext) {
                JsonObject jo = new JsonObject();
                jo.add("article", new JsonPrimitive(orderRow.getArticle().getArticleId()));
                jo.add("amount", new JsonPrimitive(orderRow.getAmount()));
                return jo;
            }
        });
    }

    public void addAddressSerializer() {
        gb.registerTypeAdapter(Address.class, new JsonSerializer<Address>() {
            @Override
            public JsonElement serialize(Address address, Type type, JsonSerializationContext jsonSerializationContext) {
                JsonObject ob = new JsonObject();
                ob.add("streetName", new JsonPrimitive(address.getStreetName()));
                ob.add("streetNumber", new JsonPrimitive(address.getStreetNumber()));
                ob.add("postalCode", new JsonPrimitive(address.getPostalCode()));
                ob.add("cityName", new JsonPrimitive(address.getCityName()));
                ob.add("country", new JsonPrimitive(address.getCountry()));
                return ob;
            }
        });
    }

    public void addCustomerContactSerializer() {
        gb.registerTypeAdapter(CustomerContact.class, new JsonSerializer<CustomerContact>() {
            @Override
            public JsonElement serialize(CustomerContact customerContact, Type type, JsonSerializationContext jsonSerializationContext) {
                JsonObject ob = new JsonObject();
                ob.add("contactPerson", new JsonPrimitive(customerContact.getContactPerson()));
                ob.add("phoneNumber", new JsonPrimitive(customerContact.getPhoneNumber()));
                ob.add("email", new JsonPrimitive(customerContact.getEmail()));
                return ob;
            }
        });
    }

    public Gson getGson(){
        return gb.serializeNulls().setPrettyPrinting().create();
    }
}
