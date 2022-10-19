package controller.orderControllers;

import controller.dpi.ParentDependencyInjection;
import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;
import model.observer.Observer;
import model.order.Order;
import model.order.Orders;
import model.site.Site;
import model.site.Sites;

import java.io.IOException;
import java.util.List;


/**
 * Controller for the OrderMenu, is also the observer (Observer pattern).
 *
 */

public class OrderMenuController implements Observer{
    @FXML
    private Button openButton, createButton, listButton, backButton;

    @FXML
    private TabPane tabPane;
    private Orders orders;
    private Sites sites;
    private WMS wms;

    /**
     * Constructor adds orders as observers (Observer pattern).
     *
     * @param wms is the facade for all facades in the model.
     */
    public OrderMenuController(WMS wms) {
        this.wms = wms;
        this.sites = wms.getSites();
        this.orders = wms.getOrders();
        wms.registerObserver(this);
    }

    /**
     * Loads all tabs.
     *
     * @throws IOException if tabs can't be added
     */

    public void initialize() throws IOException {
        loadTabs();
    }

    /**
     * Creates a tab for every site.
     *
     * @throws IOException if tabs can't be added
     */

    private void loadTabs() throws IOException{
        List<Site> s = sites.getInList();
            tabPane.getTabs().clear();

        for (Site site : s){
            Tab tab = new Tab();
            tab.setText(site.getSiteName());
            FlowPane fp = new FlowPane();
            fp.setPadding(new Insets(10,10,10,10));
            fp.setHgap(10);
            fp.setVgap(10);
            tab.setContent(fp);

            for (Order order : orders.getOrdersBySite(site)){
                ParentDependencyInjection.addInjectionMethod(
                        OrderMenuTabCardController.class, params -> new OrderMenuTabCardController(wms, order)
                );

                Parent pane = ParentDependencyInjection.load("fxml/orderViews/orderMenuTabCard.fxml");

                fp.getChildren().add(pane);

            }
            tabPane.getTabs().add(tab);
        }
    }

    /**
     * Sets MainMenu as the active View.
     *
     * @throws Exception if stage can't be loaded
     */

    public void backBtnHandler() throws Exception{
        Parent root = ParentDependencyInjection.load("fxml/mainMenu.fxml");
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /**
     * Creates modal window when opening an order.
     * @param e is the ActionEvent
     * @throws Exception if stage can't be loaded
     */

    public void openButton(ActionEvent e) throws Exception{
        Stage stage = StageDependencyInjection.load("fxml/orderViews/orderOpenModal.fxml");
        stage.setTitle("Open Order");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow() );
        stage.show();

    }

    /**
     * Creates modal window when creating an order.
     *
     * @param e is the ActionEvent
     * @throws Exception if stage cant be loaded
     */

    public void createButton(ActionEvent e) throws Exception{
        StageDependencyInjection.addInjectionMethod(
                OrderCreateModalController.class, params -> new OrderCreateModalController(wms)
        );

        Stage stage = StageDependencyInjection.load("fxml/orderViews/orderCreateModal.fxml");

        stage.setTitle("Create Order: Choose Site");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) e.getSource()).getScene().getWindow());
        stage.show();
    }

    /**
     * Updates the tabs. Method is called when orders are added or edited.
     *
     */
    @Override
    public void update() {
        try{
            loadTabs();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
