package order.tests;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import database.DataBaseAdapter;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import database.DataBaseConnection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestReadMongoDB {
    private MongoDatabase db = DataBaseConnection.getDataBase();

    @Test
    public void testReadOrder1(){
        DataBaseAdapter dba = DataBaseAdapter.getInstance();
        Document doc = dba.findAndOpenOrder("123456");

        assertTrue("this is a test order".equals(doc.get("description")));
    }

    @Test void testReadOrder2(){

        MongoCollection<Document> collection = db.getCollection("orders");
        Bson filter = Filters.in("ordernumber", "123456");
        FindIterable<Document> documents = collection.find(filter);
        MongoCursor<Document> cursor = documents.cursor();
        assertTrue("this is a test order".equals(cursor.next().get("description")));

    }

}
