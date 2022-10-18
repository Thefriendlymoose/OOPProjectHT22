package controller.orderControllers;

import controller.dpi.ParentDependencyInjection;
import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
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
import java.util.Objects;

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
        orders.registerObserver(this);
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/orderViews/orderMenuTabCard.fxml"));
                AnchorPane pane = loader.load();

                OrderMenuTabCardController controller = loader.getController();
                controller.setOrder(order);
                controller.setOrders(orders);
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
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/orderViews/orderCreateModal.fxml")));
        Stage stage = loader.load();
        OrderCreateModalController controller = loader.getController();
        controller.setOrders(orders);
        controller.setSites(sites);

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
