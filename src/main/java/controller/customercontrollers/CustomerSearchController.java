package controller.customercontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.customer.Customer;
import model.customer.CustomerModel;

import java.io.IOException;

/**
 * Controller for the search window, where the user can search for customers by id
 * @author Simon Porsgaard / doktorjevksy
 */

public class CustomerSearchController {


    @FXML
    private TextField idtextField;

    private CustomerModel model;


    public void setModel(CustomerModel model){
        this.model = model;
    }

    /**
     * Opens the Customer Info view if the customer is found
     * @param actionEvent open button is pressed
     * @throws IOException throws exception if FXML fails to load
     */

    public void openCustomerHandler(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/customerViews/customerView.fxml"));
            Stage stage = loader.load();
            CustomerInfoViewController cont = loader.getController();
            long id = parsedID();
            Customer c = openCustomer(id);
            cont.setCustomer(c);
            cont.setModel(model);
            stage.setTitle("Customer Info");

            model.registerObserver(cont);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());

            stage.show();

        } catch (Exception e) {
            idtextField.setText("ID is not valid / Customer is not found");
        }
    }

    private long parsedID() throws Exception {
        return Long.parseLong(idtextField.getText());
    }

    private Customer openCustomer(long id) throws Exception{
        if (model.getCustomerById(id) == null){
            throw new Exception();
        } else {
            return model.getCustomerById(id);
        }
    }
}
