package controller.orderControllers;

import controller.dpi.StageDependencyInjection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.customer.CustomerModel;
import model.order.Orders;
import model.site.Site;
import model.site.Sites;
import persistence.IPersistence;
import persistence.SitesDAO;

import java.io.IOException;
import java.util.Objects;

/**
 * Controller for choosing what Site the user wants to create the Order at.
 */

public class OrderCreateModalController {

    @FXML
    private Button continueButton;

    @FXML
    private ListView<Site> siteListView;

    private Orders orders;

    private Site current;
    private Sites sites;
    private CustomerModel customerModel;

    public OrderCreateModalController(Sites sites, Orders orders, CustomerModel customerModel) {
        this.sites = sites;
        this.orders = orders;
        this.customerModel = customerModel;
    }

    /**
     * Shows all available Site (Göteborg, ... etc)
     */

    public void initialize(){
        Platform.runLater(() -> {
            siteListView.getItems().addAll(sites.getInList());
            siteListView.setCellFactory(param -> new ListCell<Site>(){
                @Override
                protected void updateItem(Site s, boolean empty){
                    super.updateItem(s, empty);

                    if(empty || s == null || s.getSiteName() == null){
                        setText(null);
                    } else {
                        setText(s.getSiteName());
                    }
                }
            });

            siteListView.getSelectionModel().selectedItemProperty().addListener((observableValue, site, t1) -> {
                current = siteListView.getSelectionModel().getSelectedItem();
            });

        });

    }

    /**
     * Takes user to OrderForm modal where user can create the Order located in that site.
     *
     * @param e is the ActionEvent
     * @throws IOException throws if stage doesn't exist
     */

    public void onContinue(ActionEvent e) throws IOException {
        StageDependencyInjection.addInjectionMethod(
                OrderFormModalController.class, params -> new OrderFormModalController(orders, current, customerModel)
        );

        Stage stage = StageDependencyInjection.load("fxml/orderViews/orderFormModal.fxml");

        stage.setTitle("Create Order");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()).getOwner());
        stage.show();
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();

        orders.updateOrder();
    }

    public void setOrders(Orders orders){
        this.orders = orders;
    }

    public void setSites(Sites sites){
        this.sites = sites;
    }

}
