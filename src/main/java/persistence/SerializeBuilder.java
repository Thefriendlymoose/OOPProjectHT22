package persistence;

import com.google.gson.*;
import model.article.Article;
import model.customer.Customer;
import model.site.Site;
import model.user.User;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * Creates a custom GSON with the required custom serializers.
 * @author David al Amiri
 */
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




    public Gson getGson(){
        return gb.serializeNulls().setPrettyPrinting().create();
    }
}
