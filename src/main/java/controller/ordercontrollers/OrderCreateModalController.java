package controller.ordercontrollers;

import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;
import model.site.Site;
import model.site.Sites;

import java.io.IOException;

/**
 * Controller for choosing what Site the user wants to create the Order at.
 * @author James Pålsson
 * @author David al Amiri
 */

public class OrderCreateModalController {

    @FXML
    private ListView<Site> siteListView;


    private WMS wms;
    private Site current;
    private Sites sites;


    public OrderCreateModalController(WMS wms) {
        this.wms = wms;
        this.sites = wms.getSites();
    }

    /**
     * Shows all available Site (Göteborg, ... etc)
     */

    public void initialize(){
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

    }

    /**
     * Takes user to OrderForm modal where user can create the Order located in that site.
     *
     * @param e is the ActionEvent
     * @throws IOException throws if stage doesn't exist
     */

    public void onContinue(ActionEvent e) throws IOException {
        StageDependencyInjection.addInjectionMethod(
                OrderFormModalController.class, params -> new OrderFormModalController(wms, current)
        );

        Stage stage = StageDependencyInjection.load("fxml/orderViews/orderFormModal.fxml");

        stage.setTitle("Create Order");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()).getOwner());
        stage.show();
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();

    }

}
