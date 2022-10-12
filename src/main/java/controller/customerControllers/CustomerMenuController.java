package controller.customerControllers;

import controller.MainMenuController;
import controller.interfaces.ISubMenu;
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

import java.io.IOException;
import java.util.Objects;

public class CustomerMenuController implements ISubMenu {

    @FXML
    private Button openButton, createButton, listButton, backButton;
    private WMS wms;

    public void backBtnHandler() throws Exception{
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/mainMenu.fxml")));
        Parent root = loader.load();
        MainMenuController controller = loader.getController();
        controller.setWMS(wms);
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
        CustomerEditor editor = new CustomerEditor();
        editor.registerObserver(cont);
        cont.setEditor(editor);
        stage.show();
    }

    @Override
    public void setWMS(WMS wms) {
        this.wms = wms;
    }
}
