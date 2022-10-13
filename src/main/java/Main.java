import controller.MainMenuController;
import controller.SignInController;
import controller.articleControllers.ArticleMenuController;
import controller.customerControllers.CustomerMenuController;
import controller.dpi.DependencyInjection;
import controller.orderControllers.OrderMenuController;
import controller.siteControllers.SiteMenuController;
import controller.userControllers.UserMenuController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.WMS;
import model.article.Articles;
import model.customer.CustomerModel;
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
        CustomerModel customers = new CustomerModel(CustomersDAO.getInstance());
        this.wms = new WMS(articles, orders, sites, customers);

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
        Callback<Class<?>, Object> articleMenuController = param -> new ArticleMenuController(wms);
        Callback<Class<?>, Object> siteMenuController = param -> new SiteMenuController(wms);
        Callback<Class<?>, Object> orderMenuController = param -> new OrderMenuController(wms);
        Callback<Class<?>, Object> customerMenuController = param -> new CustomerMenuController(wms);
        Callback<Class<?>, Object> userMenuController = param -> new UserMenuController(wms);


        //Saving Factories
        DependencyInjection.addInjectionMethod(
                SignInController.class, signInControllerFactory
        );

        DependencyInjection.addInjectionMethod(
                MainMenuController.class, mainMenuControllerFactory
        );

        DependencyInjection.addInjectionMethod(
                ArticleMenuController.class, articleMenuController
        );

        DependencyInjection.addInjectionMethod(
                SiteMenuController.class, siteMenuController
        );

        DependencyInjection.addInjectionMethod(
                OrderMenuController.class, orderMenuController
        );

        DependencyInjection.addInjectionMethod(
                CustomerMenuController.class, customerMenuController
        );

        DependencyInjection.addInjectionMethod(
                UserMenuController.class, userMenuController
        );
    }



}
