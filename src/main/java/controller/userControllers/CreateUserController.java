package controller.userControllers;

import controller.siteControllers.SiteDetailsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.site.Site;
import model.site.SiteArticle;
import model.user.Role;
import model.user.User;
import persistence.SitesDAO;
import persistence.UserDAO;

import java.io.IOException;
import java.util.ArrayList;

public class CreateUserController {

    @FXML
    private Button saveButton,cancelButton;

    @FXML
    private TextField userIDTextField, firstNameField, lastNameField, userNameField, passwordField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Label userIDLabel, firstNameLabel,lastNameLabel,userNameLabel,passwordLabel,StatusLabel, roleLabel;

    @FXML
    private ComboBox<Role> roleBox;

    @FXML
    private ComboBox<Boolean> statusBox;


    private User user;
    public void onSave(ActionEvent e) throws IOException {
        User newUser = new Site(user.getNextId(), nameTextField.getText(), siteAddressTextArea.getText(),Integer.parseInt(maxCapacityTextField.getText()), new ArrayList<SiteArticle>(), new ArrayList<User>());

        sites.addSite(newSite);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/siteViews/siteDetailsModal.fxml"));
        Stage stage = loader.load();
        SiteDetailsController controller = loader.getController();
        controller.setSite(newSite);
        controller.setSites(sites);

        stage.setTitle("Site: " + newSite.getSiteId());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()).getOwner());
        stage.show();
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();

    }


    public void initialize(){
        statusBox.getItems().addAll(true, false);
        roleBox.getItems().addAll();
        userIDTextField.setText(Long.toString(UserDAO.getInstance().getNextId()));
    }











}
