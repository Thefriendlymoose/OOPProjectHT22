package controller.userControllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.user.User;

public class UserMenuCardController {

    @FXML
    private Label cardPremissionLabel, cardUserIDLabel, cardNameLabel;

    private User user;

    public void setUser(User user){
        this.user = user;
    }

    public void initialize(){
        Platform.runLater(() -> {
            cardUserIDLabel.setText(cardUserIDLabel.getText() + user.getUserId());
            cardNameLabel.setText(cardNameLabel.getText() + user.getName());
            cardPremissionLabel.setText(cardPremissionLabel.getText() + user.getPermissions());

        });
    }


}
