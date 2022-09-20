package database;

import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import model.TestUser;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class DataBaseAdapter {
    private DataBaseConnection dbc;
    private MongoDatabase db;

    public DataBaseAdapter(DataBaseConnection dbc){
        this.dbc = dbc;
        this.db = dbc.getDataBase();
    }

    // Temporary just to test to input document into a collection
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

    // Temporary just to test to input doucment into collection
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

    // Temporary scuffed user login
    public boolean signIn(String username, String password){
        try {
            MongoCollection<Document> collection = db.getCollection("test");
            Bson filter = Filters.in("username", username);
            FindIterable<Document> documents = collection.find(filter);
            MongoCursor<Document> cursor = documents.cursor();
            if(cursor.hasNext()){
                while(cursor.hasNext()){
                    Document temp = cursor.next();
                    if (username.equals(temp.get("username")) && password.equals(temp.get("password"))){
                        return true;
                    }
                }
            }
        } catch (MongoException me){

        }
        return false;
    }

    public Document findAndOpenOrder(String orderNumber){
        try {
            MongoCollection<Document> collection = db.getCollection("orders");
            Bson filter = Filters.in("ordernumber", orderNumber);
            FindIterable<Document> documents = collection.find(filter);
            MongoCursor<Document> cursor = documents.cursor();

            if(cursor.hasNext()){
                return cursor.next();
            } else {
                return null;
            }
        } catch (MongoException me){

        }
        return null;
    }
}
