import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


public class DataBaseConnection {
    private String URI = "mongodb+srv://WMS-001:grupp19@wmsproject.tlzpnr0.mongodb.net/?retryWrites=true&w=majority";
    private MongoClient client;
    private MongoDatabase db;

    public DataBaseConnection(){
        try  {
            client = MongoClients.create(URI);
            db = client.getDatabase("WMS");
        } catch (MongoException me) {
                System.err.println("Failed Connection due to: " + me);
        }
    }

    public MongoDatabase getDataBase(){
        return db;
    }

}
