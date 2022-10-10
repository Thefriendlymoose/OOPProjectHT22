package controller.customerControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.customer.CustomerEditor;

import java.io.IOException;
import java.util.Objects;

public class CustomerMenuController {

    @FXML
    private Button openButton, createButton, listButton, backButton;

    public void backBtnHandler() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/mainMenu.fxml")));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void createBtnHandler(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/customerViews/addressEdit.fxml"));
        Stage stage = loader.load();
        stage.setTitle("Create Customer");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        CustomerCreateController cont = loader.getController();

        //TODO change this nonsense
        CustomerEditor editor = new CustomerEditor();
        editor.newCustomer();
        cont.setEditor(editor);
        stage.show();
    }
}
