package ordertests;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;


import static org.junit.jupiter.api.Assertions.assertTrue;
public class TestJSON {

    @Test
    public void readFromFile(){
        final var pathToDB = Path.of("src/test/java/orderTests/orderdb.json");
        String originalString = "{\n" +
                "  \"orders\": [\n" +
                "    {\n" +
                "      \"userId\": 11,\n" +
                "      \"orderNumber\": 11,\n" +
                "      \"customerId\": 11,\n" +
                "      \"orderStatus\": \"ACTIVE\",\n" +
                "      \"priority\": true,\n" +
                "      \"orderDate\": null,\n" +
                "      \"deadline\": null,\n" +
                "      \"articles\": []\n" +
                "    },\n" +
                "    {\n" +
                "      \"userId\": 22,\n" +
                "      \"orderNumber\": 22,\n" +
                "      \"customerId\": 22,\n" +
                "      \"orderStatus\": \"ACTIVE\",\n" +
                "      \"priority\": true,\n" +
                "      \"orderDate\": null,\n" +
                "      \"deadline\": null,\n" +
                "      \"articles\": []\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        String fileContents;
        try{
            fileContents =  new String(Files.readAllBytes(pathToDB));
        }catch(Exception e){
            fileContents = "";
        }
        assertTrue(originalString.equals(fileContents));
    }
/*
    @Test
    public void ordersDBJSONToClass(){
        Gson gson = new Gson();

        final var pathToDB = Path.of("src/test/java/orderTests/orderdb.json");

        String fileContents;
        try{
            fileContents =  new String(Files.readAllBytes(pathToDB));
        }catch(Exception e){
            fileContents = "";
        }

        DBJOrders orders = gson.fromJson(fileContents, DBJOrders.class);
        System.out.println(orders.toString());
        assertTrue(orders.getOrders().get(0).getUserId() == 11);

    }
*/

/*    @Test
    public void writeToFile() throws IOException {

        Gson gsonReader = new Gson();
        String originalString = "{\n" +
                "  \"orders\": [\n" +
                "    {\n" +
                "      \"userId\": 11,\n" +
                "      \"orderNumber\": 11,\n" +
                "      \"customerId\": 11,\n" +
                "      \"orderStatus\": \"ACTIVE\",\n" +
                "      \"priority\": true,\n" +
                "      \"orderDate\": null,\n" +
                "      \"deadline\": null,\n" +
                "      \"articles\": []\n" +
                "    },\n" +
                "    {\n" +
                "      \"userId\": 22,\n" +
                "      \"orderNumber\": 22,\n" +
                "      \"customerId\": 22,\n" +
                "      \"orderStatus\": \"ACTIVE\",\n" +
                "      \"priority\": true,\n" +
                "      \"orderDate\": null,\n" +
                "      \"deadline\": null,\n" +
                "      \"articles\": []\n" +
                "    }\n" +
                "  ]\n" +
                "}";


        DBJOrders orders = gsonReader.fromJson(originalString, DBJOrders.class);
        orders.getOrders().add(new Order(33,33,33,OrderStatus.ACTIVE,true,null,null,new ArrayList<>()));

        final var pathToDB = Path.of("src/test/java/orderTests/orderdbTemp.json");
        try {
            Gson gson = new Gson();
            String jsonOrders = gson.toJson(orders);
            Files.write( pathToDB, jsonOrders.getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertTrue(Files.exists(pathToDB));
        assertTrue(orders.getOrders().get(0).getUserId()==11 && orders.getOrders().get(1).getUserId()==22 && orders.getOrders().get(2).getUserId()==33);
        Files.delete(pathToDB);
    }*/

    @Test
    public void editFromFile(){

    }
}
