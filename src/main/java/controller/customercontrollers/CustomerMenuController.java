package controller.customercontrollers;

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

/**
 * Responsibility: controls the main customer view
 * Used by:
 * Uses: CustomerModel, WMS
 * @author Simon Porsgaard / doktorjevksy
 */

public class CustomerMenuController implements Observer {

    @FXML
    private VBox customerBox;

    @FXML
    private Button backButton;
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

    /**
     * Is triggered by the event of a user pressing the Back button in the main Customer View window
     * Leads the user back to the Main Menu
     * @throws Exception Throws an exception when the FXML fails to load
     */

    public void backBtnHandler() throws Exception{
        Parent root = ParentDependencyInjection.load("fxml/mainMenu.fxml");
        model.unregisterObserver(this);
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /**
     * Opens the CustomerEditor view in a modal window
     *
     * @param e Create Button is pressed
     * @throws IOException Trows an exception when the FXML fails to load
     */

    public void createBtnHandler(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/customerViews/customerEdit.fxml"));
        Stage stage = loader.load();
        stage.setTitle("Create Customer");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        CustomerEditController cont = loader.getController();

        CustomerEditor editor = model.newCustomer();
        editor.registerObserver(cont);
        cont.setEditor(editor);
        stage.show();
    }

    /**
     * Repaints the list of Customers currently present in the Customer Model
     */
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
