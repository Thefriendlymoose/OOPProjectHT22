package controller.customercontrollers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.customer.CustomerContact;
import model.customer.CustomerEditor;
import model.observer.Observer;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Responsibility: controls the view for editing, deleting and adding contacts
 * Used by:
 * Uses: CustomerEditor, ContactEditCardController
 * @author Simon Porsgaard / doktorjevksy
 */

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

    public void addContactHandler(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/customerViews/contactCreate.fxml"));
        Stage stage = loader.load();
        ContactCreateController cont = loader.getController();
        cont.setEditor(editor);

        stage.setTitle("Create new customer contact");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }

    public void closeHandler(ActionEvent actionEvent) {
        editor.unregisterObserver(this);
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }



    @Override
    public void update() {
        try {
            repaint();
        } catch (IOException e){
            throw new RuntimeException(e);
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
            cont.setEditor(editor);
            contactBox.getChildren().add(ap);
        }
    }
}
