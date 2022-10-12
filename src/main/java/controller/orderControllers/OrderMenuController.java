package controller.orderControllers;

import controller.MainMenuController;
import controller.interfaces.ISubMenu;
import javafx.application.Platform;
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

public class OrderMenuController implements Observer, ISubMenu {
    @FXML
    private Button openButton, createButton, listButton, backButton;

    @FXML
    private TabPane tabPane;

    private Orders orders;
    private Sites sites;
    private WMS wms;

    public void initialize() throws IOException {
        Platform.runLater(() -> {
            this.orders = wms.getOrders();
            this.sites = wms.getSites();
            orders.registerObserver(this);
            try {
                loadTabs();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

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
                controller.setSite(site);
                fp.getChildren().add(pane);

            }
            tabPane.getTabs().add(tab);
        }
    }

    public void backBtnHandler() throws Exception{
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/mainMenu.fxml")));
        Parent root = loader.load();
        MainMenuController controller = loader.getController();
        controller.setWMS(wms);
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void openButton(ActionEvent e) throws Exception{
        Stage stage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/orderViews/orderOpenModal.fxml")));
        stage.setTitle("Open Order");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow() );
        stage.show();

    }

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

    @Override
    public void update() {
        try{
            loadTabs();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setWMS(WMS wms) {
        this.wms = wms;
    }
}
