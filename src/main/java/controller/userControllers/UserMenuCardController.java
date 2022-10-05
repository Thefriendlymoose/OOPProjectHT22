package controller.userControllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.User;
import model.site.Site;

public class UserMenuCardController {

    @FXML
    private Label cardSiteNameLabel, cardNumberArticlesLabel, cardNumberEmployeesLabel;

    private User user;

    public void setCard(User user){
        this.user = user;
    }

    public void initialize(){
        Platform.runLater(() -> {
            cardSiteNameLabel.setText(cardSiteNameLabel.getText() + user.getUserName());
            cardNumberArticlesLabel.setText(cardNumberArticlesLabel.getText() + user.getPassword());
            cardNumberEmployeesLabel.setText(cardNumberEmployeesLabel.getText() + user.getName());

        });
    }


}
