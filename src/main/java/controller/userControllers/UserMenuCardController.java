package controller.userControllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.User;

public class UserMenuCardController {

    @FXML
    private Label cardUserNameLabel, cardPasswordLabel, cardNameLabel;

    private User user;

    public void setCard(User user){
        this.user = user;
    }

    public void initialize(){
        Platform.runLater(() -> {
            cardUserNameLabel.setText(cardUserNameLabel.getText() + user.getUserName());
            cardPasswordLabel.setText(cardPasswordLabel.getText() + user.getPassword());
            cardNameLabel.setText(cardNameLabel.getText() + user.getName());

        });
    }


}
