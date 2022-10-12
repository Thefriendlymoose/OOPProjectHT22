package controller.customerControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.customer.CustomerContact;
import model.customer.CustomerEditor;
import model.observer.Observer;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ContactEditorController implements Observer {

    private CustomerEditor editor;

    @FXML
    private VBox contactBox;

    public void initialize(){
        Platform.runLater(() -> {
            update();
        });
    }

    public void setEditor(CustomerEditor editor){
        this.editor = editor;
    }

    public void addContactHandler(ActionEvent actionEvent) {
    }

    public void saveHandler(ActionEvent actionEvent) {
    }

    public void cancelHandler(ActionEvent actionEvent) {
    }

    @Override
    public void update() {
        try {
            repaint();
        } catch (IOException e){
            throw new RuntimeException("oh no");
        }


    }

    private void repaint() throws IOException {
        List<CustomerContact> cs = editor.getContacts();
        contactBox.getChildren().clear();
        for (CustomerContact c : cs){
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/customerViews/contactEditCard.fxml")));
            AnchorPane ap = loader.load();
            ContactEditCardController cont = loader.getController();
            cont.setCustomerContact(c);
            contactBox.getChildren().add(ap);
        }
    }

}
