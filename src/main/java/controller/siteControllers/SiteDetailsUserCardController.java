package controller.siteControllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.User;


public class SiteDetailsUserCardController {

    @FXML
    private Label cardNameLabel;

    @FXML
    private Button cardGoToButton;

    private User user;

    @FXML
    public void initialize(){
        Platform.runLater(() -> {
            cardNameLabel.setText(cardNameLabel.getText() + user.getName());

            cardGoToButton.setOnAction(ActionEvent -> {
                System.out.println("open user details window");
            });

        });
    }

    public void setUser(User user){
        this.user = user;
    }

}
