package controller.customerControllers;

import controller.dpi.ParentDependencyInjection;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.WMS;
import model.customer.Customer;
import model.customer.CustomerEditor;
import model.customer.CustomerModel;
import model.observer.Observer;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CustomerMenuController implements Observer {

    @FXML
    private VBox customerBox;

    @FXML
    private Button openButton, createButton, backButton;
    private WMS wms;
    private CustomerModel model;

    public CustomerMenuController(WMS wms) {
        this.wms = wms;
        model = wms.getCustomerModel();
        model.registerObserver(this);
    }

    public void initialize(){
        Platform.runLater(() -> update());
    }

    public void backBtnHandler() throws Exception{
        Parent root = ParentDependencyInjection.load("fxml/mainMenu.fxml");
        model.unregisterObserver(this);
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void createBtnHandler(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/customerViews/customerEdit.fxml"));
        Stage stage = loader.load();
        stage.setTitle("Create Customer");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        CustomerEditController cont = loader.getController();

        //TODO change this nonsense or maybe not?
        CustomerEditor editor = model.newCustomer();
        editor.registerObserver(cont);
        cont.setEditor(editor);
        stage.show();
    }

    @Override
    public void update() {
        try {
            loadCustomerCards();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerCards() throws IOException {
        List<Customer> customerList = model.getCustomerList();
        customerBox.getChildren().clear();
        for (Customer c : customerList){
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/customerViews/customerCard.fxml")));
            AnchorPane pane = loader.load();

            CustomerCardController cont = loader.getController();
            cont.setCustomer(c);
            cont.setModel(model);

            customerBox.getChildren().add(pane);
        }
    }
}
