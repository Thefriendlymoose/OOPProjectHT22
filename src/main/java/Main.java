import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Authentication.AuthenticationStatus;
import model.Authentication.UserAuthentication;
import model.article.Articles;
import persistence.*;

import java.time.LocalDateTime;
import java.util.HashMap;


public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("fxml/template.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("WMS");
        stage.setScene(scene);
        stage.show();

    }
}
