package orderTests;

import com.google.gson.Gson;
import model.order.Order;
import model.order.OrderStatus;
import org.junit.jupiter.api.Test;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;


public class TestConvertJSONtoJavaObject {

    @Test
    public void testConvert(){
        Order orderTest1 = new Order(1,1,1, OrderStatus.ACTIVE,true,null,null,null);
        Gson gson = new Gson();
        String jsonTest = gson.toJson(orderTest1);

        assertTrue("{\"userId\":1,\"orderNumber\":1,\"customerId\":1,\"orderStatus\":\"ACTIVE\",\"priority\":true}".equals(jsonTest));

    }
}
