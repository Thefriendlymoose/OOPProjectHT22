package controller.orderControllers;

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
import model.order.Order;
import model.order.Orders;
import model.site.Site;
import persistence.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class OrderMenuController {
    @FXML
    private Button openButton, createButton, listButton, backButton;

    @FXML
    private TabPane tabPane;


    private IPersistence<Site> testDao = SitesDAO.getInstance();
    private Orders orders = new Orders();

    public  void initialize() throws IOException {
        List<Site> sites = testDao.getAll();

        for (Site site : sites){
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
                controller.setSite(site);

                fp.getChildren().add(pane);

            }

            tabPane.getTabs().add(tab);
        }

    }

    public void backBtnHandler() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/mainMenu.fxml")));
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
        Stage stage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/orderViews/orderCreateModal.fxml")));
        stage.setTitle("Create Order: Choose Site");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) e.getSource()).getScene().getWindow());
        stage.show();
    }



//    public void listButton() throws Exception{
//        System.out.println("LIST");
//    }




}
