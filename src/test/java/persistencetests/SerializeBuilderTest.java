package persistencetests;

import com.google.gson.Gson;
import model.article.Article;
import model.customer.Customer;
import model.site.Site;
import model.user.Role;
import model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.SerializeBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerializeBuilderTest {

    SerializeBuilder sb;

    @BeforeEach
    public void init(){
        this.sb = new SerializeBuilder();
    }

    @Test
    public void serializeArticle(){
        sb.addArticleSerializer();
        Gson gson = sb.getGson();
        Article art = new Article(1, null, null, null, null, 25, 25, null, null, null);
        String serialized = gson.toJson(art);
        assertEquals("1", serialized);
    }

    @Test
    public void serializeSite(){
        sb.addSiteSerializer();
        Gson gson = sb.getGson();
        Site site = new Site(1, null, null, 500, null, null);
        String serialized = gson.toJson(site);
        assertEquals("1", serialized);
    }

    @Test
    public void serializeCustomer(){
        sb.addCustomerSerializer();
        Gson gson = sb.getGson();
        Customer customer = new Customer(new ArrayList<>(), null, null, 1, 24, "testName");
        String serialized = gson.toJson(customer);
        assertEquals("1", serialized);
    }

    @Test
    public void serializeUser() {
        sb.addUserSerializer();
        Gson gson = sb.getGson();
        User user = new User(1, "testuser", "1234", "test name", true, Role.getAdmin());
        String serialized = gson.toJson(user);
        assertEquals("1", serialized);
    }

    @Test
    public void serializeLocalDateTime() {
        sb.addLocalDateTimeSerializer();
        Gson gson = sb.getGson();
        LocalDateTime ldt = LocalDateTime.now();
        String serialized = gson.toJson(ldt);

        assertEquals('"'+ldt.toString()+'"', serialized);
    }
}
