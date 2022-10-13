package controller.customerControllers;

import controller.dpi.DependencyInjection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.WMS;
import model.customer.CustomerEditor;
import model.customer.CustomerModel;

import java.io.IOException;
import java.util.Objects;

public class CustomerMenuController {

    @FXML
    private Button openButton, createButton, listButton, backButton;
    private WMS wms;
    private CustomerModel model;

    public CustomerMenuController(WMS wms) {
        this.wms = wms;
        model = wms.getCustomerModel();
    }

    public void backBtnHandler() throws Exception{
        Parent root = DependencyInjection.load("fxml/mainMenu.fxml");
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

}
