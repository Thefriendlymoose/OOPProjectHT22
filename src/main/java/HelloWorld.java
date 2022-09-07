import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.UnknownHostException;

public class HelloWorld extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://WMSProject:grupp19@wmsproject.tlzpnr0.mongodb.net/?retryWrites=true&w=majority"));


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
