import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;

public class DataBaseAdapter {
    MongoDatabase db;

    public DataBaseAdapter(MongoDatabase db){
        this.db = db;
    }

    public void createTestDocument(){
        try{
            MongoCollection<Document> collection = db.getCollection("test");
            try {
                InsertOneResult result = collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append("name", "david")
                        .append("age", "29"));
            } catch (MongoException me){
                System.out.println(me);
            }
        } catch (MongoException me){
            System.out.println(me);
        }
    }

    public void createTestUser(TestUser testUser){
        try{
            MongoCollection<Document> collection = db.getCollection("test");
            try {
                InsertOneResult result = collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append("name", testUser.getName())
                        .append("age", testUser.getAge()));
            } catch (MongoException me){
                System.out.println(me);
            }
        } catch (MongoException me){
            System.out.println(me);
        }
    }
}
