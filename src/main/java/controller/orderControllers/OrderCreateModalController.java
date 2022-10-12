package controller.orderControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.order.Orders;
import model.site.Site;
import model.site.Sites;
import persistence.IPersistence;
import persistence.SitesDAO;

import java.io.IOException;
import java.util.Objects;

public class OrderCreateModalController {

    @FXML
    private Button continueButton;

    @FXML
    private ListView<Site> siteListView;

    private Orders orders;

    private Site current;
    private Sites sites;
    private IPersistence<Site> jsonDao = SitesDAO.getInstance();


    public void initialize(){

        siteListView.getItems().addAll(jsonDao.getAll());

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

    public void onContinue(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/orderViews/orderFormModal.fxml")));
        Stage stage = loader.load();

        OrderFormModalController cont = loader.getController();
        cont.setSite(current);
        cont.setOrders(orders);

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
