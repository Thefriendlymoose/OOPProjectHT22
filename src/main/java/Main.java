import controller.SignInController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Authentication.AuthenticationStatus;
import model.Authentication.UserAuthentication;
import model.WMS;
import model.article.Articles;
import model.order.Orders;
import model.site.Sites;
import persistence.*;

import java.time.LocalDateTime;
import java.util.HashMap;


public class Main extends Application {

    private WMS wms;


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Articles articles = new Articles(ArticlesDAO.getInstance().getAllMap());
        Orders orders = new Orders(OrderDAO.getInstance().getAllMap());
        Sites sites = new Sites(SitesDAO.getInstance().getAllMap());
        this.wms = new WMS(articles, orders, sites);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/startScreen.fxml"));
        Parent root = loader.load();
        SignInController controller = loader.getController();
        controller.setWMS(wms);


        Scene scene = new Scene(root);
        stage.setTitle("WMS");
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void stop(){

    }

}
