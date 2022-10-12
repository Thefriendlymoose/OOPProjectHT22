import controller.MainMenuController;
import controller.SignInController;
import controller.dpi.DependencyInjection;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.WMS;
import model.article.Articles;
import model.order.Orders;
import model.site.Sites;
import persistence.*;

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

        setUpDependencyInjector();

        Parent root = DependencyInjection.load("fxml/startScreen.fxml");

        Scene scene = new Scene(root);
        stage.setTitle("WMS");
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void stop(){

    }

    private void setUpDependencyInjector() {
        //Factories
        Callback<Class<?>, Object> signInControllerFactory = param -> new SignInController(wms);
        Callback<Class<?>, Object> mainMenuControllerFactory = param -> new MainMenuController(wms);

        //Saving Factories
        DependencyInjection.addInjectionMethod(
                SignInController.class, signInControllerFactory
        );

        DependencyInjection.addInjectionMethod(
                MainMenuController.class, mainMenuControllerFactory
        );
    }



}
