package orderTests;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

public class TestAddOrderToDB {


    @Test
    public void testAdd(){
        final var path = Path.of("src/test/java/order/tests/orderdb.json");
        Gson gson = new Gson();
//    Object obj = gson.fromJson(orderdb.json, Object.class);
        System.out.println(path.toAbsolutePath().toString());


    }

}
