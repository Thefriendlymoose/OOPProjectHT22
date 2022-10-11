package controller.siteControllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import model.User;
import model.site.Site;
import model.site.Sites;

import java.util.Optional;


public class SiteDetailsUserCardController {

    @FXML
    private Label cardNameLabel;

    @FXML
    private Button cardGoToButton, deleteButton;

    private User user;
    private Site site;
    private Sites sites;

    @FXML
    public void initialize(){
        Platform.runLater(() -> {
            cardNameLabel.setText(cardNameLabel.getText() + user.getName());

            cardGoToButton.setOnAction(actionEvent -> {
                System.out.println("open user details window");
            });

            deleteButton.setOnAction(actionEvent -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Deletion");
                alert.setHeaderText("The Employee will be removed from this site");
                alert.setContentText("Are you sure? Will not be able to be revered");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.CANCEL){
                    System.out.println("Clicked Cancel");
                } else {
                    site.removeEmployee(user);
                    sites.updateSite();
                }
            });

        });
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public void setSites(Sites sites) {
        this.sites = sites;
    }
}
