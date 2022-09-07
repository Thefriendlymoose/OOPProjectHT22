
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class HelloWorld extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        String MONGO_URI = "mongodb+srv://WMSProject:grupp19@wmsproject.tlzpnr0.mongodb.net/?retryWrites=true&w=majority";
        MongoClient mongoClient = MongoClients.create(MONGO_URI);

        try{
            System.out.println("MongoDB Server is up:- "+mongoClient.getClusterDescription());
        } catch (Exception e){
            System.out.println("Failed to connect");
        }

        System.out.println(mongoClient.getDatabase("WMS").getName());


        stage.setTitle("WMS");
        MenuBar menuBar = new MenuBar();
        VBox vBox = new VBox(menuBar);

        Menu file = new Menu("File");
        MenuItem add = new MenuItem("Edit");
        MenuItem delete = new MenuItem("Delete");
        SeparatorMenuItem separator = new SeparatorMenuItem();
        MenuItem logIn = new MenuItem("Log In");
        MenuItem logOut = new MenuItem("Log Out");

        file.getItems().add(add);
        file.getItems().add(delete);
        file.getItems().add(separator);
        file.getItems().add(logIn);
        file.getItems().add(logOut);

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
    }
}
