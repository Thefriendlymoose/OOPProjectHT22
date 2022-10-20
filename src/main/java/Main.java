import controller.MainMenuController;
import controller.MenuController;
import controller.SignInController;
import controller.articleControllers.ArticleFormController;
import controller.articleControllers.ArticleMenuController;
import controller.articleControllers.ArticleOpenDetailsModalController;
import controller.customerControllers.CustomerMenuController;
import controller.dpi.ParentDependencyInjection;
import controller.dpi.StageDependencyInjection;
import controller.orderControllers.OrderMenuController;
import controller.orderControllers.OrderOpenController;
import controller.siteControllers.SiteCreateController;
import controller.siteControllers.SiteMenuController;
import controller.siteControllers.SiteOpenDetailsController;
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
import model.user.Users;
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
        Users users = new Users(UserDAO.getInstance().getAllMap());
        this.wms = new WMS(articles, orders, sites, customers, users);

        setUpSceneDependencyInjector();
        setUpStageDependencyInjector();

        Parent root = ParentDependencyInjection.load("fxml/startScreen.fxml");

        Scene scene = new Scene(root);
        stage.setTitle("WMS");
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void stop(){

    }

    private void setUpSceneDependencyInjector() {
        //Factories
        Callback<Class<?>, Object> signInControllerFactory = param -> new SignInController(wms);
        Callback<Class<?>, Object> menuBarControllerFactory = param -> new MenuController(wms);
        Callback<Class<?>, Object> mainMenuControllerFactory = param -> new MainMenuController(wms);
        Callback<Class<?>, Object> articleMenuController = param -> new ArticleMenuController(wms);
        Callback<Class<?>, Object> siteMenuController = param -> new SiteMenuController(wms);
        Callback<Class<?>, Object> orderMenuController = param -> new OrderMenuController(wms);
        Callback<Class<?>, Object> customerMenuController = param -> new CustomerMenuController(wms);
        Callback<Class<?>, Object> userMenuController = param -> new UserMenuController(wms);


        //Saving Factories
        ParentDependencyInjection.addInjectionMethod(
                SignInController.class, signInControllerFactory
        );

        ParentDependencyInjection.addInjectionMethod(
                MenuController.class, menuBarControllerFactory
        );

        ParentDependencyInjection.addInjectionMethod(
                MainMenuController.class, mainMenuControllerFactory
        );

        ParentDependencyInjection.addInjectionMethod(
                ArticleMenuController.class, articleMenuController
        );

        ParentDependencyInjection.addInjectionMethod(
                SiteMenuController.class, siteMenuController
        );

        ParentDependencyInjection.addInjectionMethod(
                OrderMenuController.class, orderMenuController
        );

        ParentDependencyInjection.addInjectionMethod(
                CustomerMenuController.class, customerMenuController
        );

        ParentDependencyInjection.addInjectionMethod(
                UserMenuController.class, userMenuController
        );
    }

    private void setUpStageDependencyInjector() {
        //Factories
        //Article
        Callback<Class<?>, Object> articleOpenDetailsModal = param -> new ArticleOpenDetailsModalController(wms);
        Callback<Class<?>, Object> articleFormModal = param -> new ArticleFormController(wms);

        //Orders
        Callback<Class<?>, Object> orderOpenModal = param -> new OrderOpenController(wms);

        //Sites
        Callback<Class<?>, Object> siteOpenModal = param -> new SiteOpenDetailsController(wms);
        Callback<Class<?>, Object> siteCreateModal = param -> new SiteCreateController(wms);

        //Saving Factories
        StageDependencyInjection.addInjectionMethod(
                ArticleOpenDetailsModalController.class, articleOpenDetailsModal
        );

        StageDependencyInjection.addInjectionMethod(
                ArticleFormController.class, articleFormModal
        );

        StageDependencyInjection.addInjectionMethod(
                OrderOpenController.class, orderOpenModal
        );

        StageDependencyInjection.addInjectionMethod(
                SiteOpenDetailsController.class, siteOpenModal
        );

        StageDependencyInjection.addInjectionMethod(
                SiteCreateController.class, siteCreateModal
        );
    }

}
