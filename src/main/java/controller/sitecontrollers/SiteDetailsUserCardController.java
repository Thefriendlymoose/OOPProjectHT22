package controller.sitecontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import model.WMS;
import model.user.User;
import model.site.Site;

import java.util.Optional;

/**
 * controller for the card which is available in the site details
 * which is used to show the user.
 *
 * @author David Al Amiri
 */
public class SiteDetailsUserCardController {

    @FXML
    private Label cardNameLabel;

    private User user;
    private Site site;
    private WMS wms;

    public SiteDetailsUserCardController(WMS wms, Site site, User user) {
        this.wms = wms;
        this.site = site;
        this.user = user;
    }

    /**
     * Initializes the card label with data from the user
     */
    @FXML
    public void initialize(){
        cardNameLabel.setText(cardNameLabel.getText() + user.getName());

    }

    /**
     * Handles the event when a user clicks on the goto button in the modal
     * Opens user details modal
     * @param e
     */
    public void onGoTo(ActionEvent e){
        System.out.println("open user details window");
    }

    /**
     * Handles the event when a user clicks on the delete button in the modal
     * Deletes the user from the site
     * Prompts the user to confirm deletion or to cancel the deletion
     * @param e
     */
    public void onDelete(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("The Employee will be removed from this site");
        alert.setContentText("Are you sure? Will not be able to be revered");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL){
            System.out.println("Clicked Cancel");
        } else {
            site.removeEmployee(user);
            wms.updateSite();
        }
    }
}
