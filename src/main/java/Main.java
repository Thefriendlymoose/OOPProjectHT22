import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Authentication.AuthenticationStatus;
import model.Authentication.UserAuthentication;
import model.article.Articles;
import model.order.Orders;
import model.site.Sites;
import persistence.*;

import java.time.LocalDateTime;
import java.util.HashMap;


public class Main extends Application {

    private Articles articles;
    private Orders orders;
    private Sites sites;



    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.articles = new Articles(ArticlesDAO.getInstance().getAllMap());
        this.orders = new Orders(OrderDAO.getInstance().getAllMap());
        this.sites = new Sites(SitesDAO.getInstance().getAllMap());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/startScreen.fxml"));
        Parent root = loader.load();


        Scene scene = new Scene(root);
        stage.setTitle("WMS");
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void stop(){

    }

}
