package controller.customerControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.customer.Customer;
import model.customer.CustomerModel;

import java.io.IOException;

public class CustomerCardController {

    private CustomerModel model;
    private Customer customer;

    @FXML
    private Label idLabel;

    @FXML
    private Label companyLabel;

    public void setCustomer(Customer c){
        customer = c;
    }

    public void initialize(){
        Platform.runLater(() -> {
            idLabel.setText(Long.toString(customer.getCustomerID()));
            companyLabel.setText(customer.getCompanyName());
        });
    }

    private void setModel(CustomerModel model){
        this.model = model;
    }

    public void openButtonHandler(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/customerViews/customerView.fxml"));
        Stage stage = loader.load();
        CustomerInfoViewController cont = loader.getController();
        cont.setCustomer(customer);
        stage.setTitle(customer.getCompanyName());

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());

        stage.show();

    }
}
