package database;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import model.TestUser;
import model.pojos.ArticlePojo;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataBaseAdapter {
    private MongoDatabase db;

    private static DataBaseAdapter instance;

    private DataBaseAdapter(){
        this.db = DataBaseConnection.getDataBase();
    }

    public static DataBaseAdapter getInstance(){
        if(instance == null){
            synchronized (DataBaseAdapter.class){
                if (instance == null){
                    instance = new DataBaseAdapter();
                }
            }
        }
        return instance;
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

    // testing to create a pojo and retrieve pojo
    public void insertArticlePojo(){
        ArticlePojo test = new ArticlePojo(new ObjectId(), "art123456789", "this is a test article", LocalDateTime.now(), "Active", LocalDateTime.now());
        getArticleCollection().insertOne(test);

        List<ArticlePojo> temp = new ArrayList<>();
        getArticleCollection().find().into(temp);
        System.out.println(temp);
    }

    private MongoCollection<ArticlePojo> getArticleCollection(){
        return db.getCollection("articles", ArticlePojo.class);
    }
}
