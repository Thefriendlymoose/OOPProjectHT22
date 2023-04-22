import controller.financecontrollers.FinanceMainController;
import controller.menucontrollers.MainMenuController;
import controller.menucontrollers.MenuController;
import controller.SignInController;
import controller.articlecontrollers.ArticleFormController;
import controller.articlecontrollers.ArticleMenuController;
import controller.articlecontrollers.ArticleOpenDetailsModalController;
import controller.customercontrollers.CustomerMenuController;
import controller.dpi.ParentDependencyInjection;
import controller.dpi.StageDependencyInjection;
import controller.ordercontrollers.OrderMenuController;
import controller.ordercontrollers.OrderOpenController;
import controller.sitecontrollers.SiteCreateController;
import controller.sitecontrollers.SiteMenuController;
import controller.sitecontrollers.SiteOpenDetailsController;
import controller.usercontrollers.UserMenuController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.WMS;
import model.article.Articles;
import model.authentication.UserAuthentication;
import model.customer.CustomerModel;
import model.finance.financeModel.FinanceModel;
import model.finance.financeModel.SiteFinanceModel;
import model.order.Orders;
import model.site.Sites;
import model.user.Users;
import persistence.IPersistence;
import persistence.dataaccessobjects.*;

import java.util.Map;

/**
 * The main class which is run when the application is started.
 *
 * @author David al Amiri
 * @author James Pålsson
 * @author Simon Porsgaard
 * @author Alexander Hyyppa
 */
public class Main extends Application {

    private WMS wms;
    private IPersistence<SiteFinanceModel> financeDao;

    public static void main(String[] args){
        launch(args);
    }

    /**
     * Is responsible to start the program.
     * It gets all the saved data and initializes a new WMS object.
     * It also sets up the dependencyInjectors for the controllers
     * @param stage
     * @throws Exception throws exception if the FXML fails to load.
     */
    @Override
    public void start(Stage stage) throws Exception {

        financeDao = new FinanceModelDAO("src/main/resources/finance.json");
        Map<Long, SiteFinanceModel> financeMap = financeDao.getAllMap();

        FinanceModel financeModel = new FinanceModel(financeMap);
        Articles articles = new Articles(ArticlesDAO.getInstance().getAllMap());
        Orders orders = new Orders(OrderDAO.getInstance().getAllMap());
        Sites sites = new Sites(SitesDAO.getInstance().getAllMap());
        CustomerModel customers = new CustomerModel(CustomersDAO.getInstance().getAllMap());
        Users users = new Users(UserDAO.getInstance().getAllMap());
        UserAuthentication ua = new UserAuthentication();
        this.wms = new WMS(articles, orders, sites, customers, users, ua, financeModel);


        setUpSceneDependencyInjector();
        setUpStageDependencyInjector();

        Parent root = ParentDependencyInjection.load("fxml/startScreen.fxml");

        Scene scene = new Scene(root);
        stage.setTitle("WMS");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * the stop method is run when the application is closed
     * Stop is responsible to send the save command to the DAOs.
     */
    @Override
    public void stop(){
        // should save to dao
        ArticlesDAO.getInstance().save(wms.getArticles().getInList());
        SitesDAO.getInstance().save(wms.getSites().getInList());
        OrderDAO.getInstance().save(wms.getOrders().getInList());
        UserDAO.getInstance().save(wms.getUsers().getInList());
        CustomersDAO.getInstance().save(wms.getCustomerModel().getCustomerList());
        try {
            financeDao.save(wms.getFinanceModels().values().stream().toList());
        } catch (Exception ignore) {} // it throws and exception if we're not authorized, in which case the finance model is empty
    }

    /**
     * Sets up dependency injection for controllers that only need the wms injected.
     * Specifically for views that are nodes and not stages.
     */
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
        Callback<Class<?>, Object> financeMenuController = p -> new FinanceMainController(wms);


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

        ParentDependencyInjection.addInjectionMethod(FinanceMainController.class, financeMenuController);
    }

    /**
     * Sets up dependency injection for controllers that only need the wms injected.
     * Specifically for views that are stages.
     */
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
