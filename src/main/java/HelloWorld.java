

import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;


public class HelloWorld extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        String MONGO_URI = "mongodb+srv://WMSProject:grupp19@wmsproject.tlzpnr0.mongodb.net/?retryWrites=true&w=majority";


        stage.setTitle("WMS");
        MenuBar menuBar = new MenuBar();
        VBox vBox = new VBox(menuBar);

        Menu file = new Menu("File");
        MenuItem add = new MenuItem("Edit");
        MenuItem delete = new MenuItem("Delete");
        SeparatorMenuItem separator = new SeparatorMenuItem();
        MenuItem logIn = new MenuItem("Log In");
        MenuItem logOut = new MenuItem("Log Out");
        MenuItem info = new MenuItem("Info");

        file.getItems().add(add);
        file.getItems().add(delete);
        file.getItems().add(separator);
        file.getItems().add(logIn);
        file.getItems().add(logOut);
        file.getItems().add(info);

        Menu edit = new Menu("Edit");
        Menu view = new Menu("View");
        Menu help = new Menu("Help");

        menuBar.getMenus().add(file);
        menuBar.getMenus().add(edit);
        menuBar.getMenus().add(view);
        menuBar.getMenus().add(help);

        Scene scene = new Scene(vBox, 640, 480);
        stage.setScene(scene);
        stage.show();

        ConnectionString connectionString = new ConnectionString("mongodb+srv://WMS-001:grupp19@wmsproject.tlzpnr0.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();



        try (MongoClient mongoClient = MongoClients.create(settings);) {
            MongoDatabase database = mongoClient.getDatabase("WMS");
            MongoCollection<Document> collection = database.getCollection("test");
            System.out.println("Connected");
            try {
                InsertOneResult result = collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append("name", "james")
                        .append("age","26"));
                System.out.println("Success! Inserted document id: " + result.getInsertedId());
            } catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }
        }
    }
}
