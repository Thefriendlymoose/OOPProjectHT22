package controller.sitecontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.WMS;
import model.user.User;
import model.site.Site;
import model.user.Users;

import java.util.Optional;

/**
 * Controller for the view which is used to add users to a site
 *
 * @author David Al Amiri
 */
public class SiteDetailsUserAddModalController {

    @FXML
    private ListView<User> chooseUserListView;

    private User current;

    private Site site;
    private WMS wms;
    private Users users;

    public SiteDetailsUserAddModalController(WMS wms, Site site) {
        this.wms = wms;
        this.site = site;
        this.users = wms.getUsers();
    }

    /**
     * Initializes the listview with all available users in wms
     */
    public void initialize(){
        chooseUserListView.getItems().addAll(users.getAllUsers());

        chooseUserListView.setCellFactory(param -> new ListCell<User>(){
            @Override
            protected void updateItem(User s, boolean empty){
                super.updateItem(s, empty);

                if(empty || s == null || s.getName() == null){
                    setText(null);
                } else {
                    setText(s.getName());
                }
            }
        });

        chooseUserListView.getSelectionModel().selectedItemProperty().addListener((observableValue, site, t1) -> {
            current = chooseUserListView.getSelectionModel().getSelectedItem();
        });
    }

    /**
     * Handles the event when the user clicks save in the modal
     * Saves the user to the site if it is not already in the site
     * @param e
     */
    public void onSave(ActionEvent e){
        if (site.addEmployee(current)){
            ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
            wms.updateSite();
        } else {
            //TODO add popup which informs user that selected already in site
            System.out.println("Employee already in site");
        }
    }

    /**
     * Handles the event where a user clicks cancel in the modal
     * Opens a modal where the user is asked to confirm cancellation or return
     * @param e
     */
    public void onCancel(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("All changes made will be cancelled");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL){
            System.out.println("Clicked Cancel");
        } else {
            ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
        }
    }

}
