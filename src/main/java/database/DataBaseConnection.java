package database;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class DataBaseConnection {
    private MongoDatabase db;
    private static DataBaseConnection instance;

    private DataBaseConnection(){
        try  {
            String URI = "mongodb+srv://WMS-001:grupp19@wmsproject.tlzpnr0.mongodb.net/?retryWrites=true&w=majority";

            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

            MongoClient client = MongoClients.create(URI);
            db = client.getDatabase("WMS").withCodecRegistry(pojoCodecRegistry);
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

    public static MongoDatabase getDataBase(){
        DataBaseConnection tempInstance = getInstance();
        return tempInstance.db;
    }


}
