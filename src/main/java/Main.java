import controller.MainMenuController;
import controller.SignInController;
import controller.articleControllers.ArticleFormController;
import controller.articleControllers.ArticleMenuController;
import controller.articleControllers.ArticleOpenDetailsModalController;
import controller.customerControllers.CustomerMenuController;
import controller.dpi.ParentDependencyInjection;
import controller.dpi.StageDependencyInjection;
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
        Callback<Class<?>, Object> articleOpenDetailsModal = param -> new ArticleOpenDetailsModalController(wms.getArticles());
        Callback<Class<?>, Object> articleFormModal = param -> new ArticleFormController(wms.getArticles());

        //Saving Factories
        StageDependencyInjection.addInjectionMethod(
                ArticleOpenDetailsModalController.class, articleOpenDetailsModal
        );

        StageDependencyInjection.addInjectionMethod(
                ArticleFormController.class, articleFormModal
        );
    }



}
