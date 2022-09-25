package orderTests;

import com.google.gson.Gson;
import model.order.Order;
import model.order.OrderStatus;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;

public class TestJSON {

    @Test
    public void testConvertFromJavaToJSON(){
        Order orderTest1 = new Order(1,1,1, OrderStatus.ACTIVE,true,null,null,null);
        Gson gson = new Gson();
        String jsonTest = gson.toJson(orderTest1);

        assertTrue("{\"userId\":1,\"orderNumber\":1,\"customerId\":1,\"orderStatus\":\"ACTIVE\",\"priority\":true}".equals(jsonTest));

    }

    @Test
    public void testAddToJSON(){
        final var pathToDB = Path.of("src/test/java/orderTests/orderdb.json");
        Gson gson = new Gson();

//        JSONParser parser = new JSONParser(new FileReader("path/to/fiue.json"));
//        return (List<MyClass>) parser.parse();


    }
}
