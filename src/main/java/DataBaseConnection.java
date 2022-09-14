import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


public class DataBaseConnection {
    private MongoDatabase db;
    private static DataBaseConnection instance;

    private DataBaseConnection(){
        try  {
            String URI = "mongodb+srv://WMS-001:grupp19@wmsproject.tlzpnr0.mongodb.net/?retryWrites=true&w=majority";
            MongoClient client = MongoClients.create(URI);
            db = client.getDatabase("WMS");
        } catch (MongoException me) {
                System.err.println("Failed Connection due to: " + me);
        }
    }

    public static DataBaseConnection getInstance(){
        if(instance == null){
            synchronized (DataBaseConnection.class){
                if(instance == null){
                    instance = new DataBaseConnection();
                }
            }
        }
        return instance;
    }

    public MongoDatabase getDataBase(){
        return db;
    }

}
