package controller.userControllers;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;
import model.user.User;
import model.user.Users;

import java.io.IOException;
import java.util.Objects;

public class UserMenuCardController {

    @FXML
    private Label cardPremissionLabel, cardUserIDLabel, cardNameLabel;

    @FXML
    private Button editUserButton, removeUserButton;

    private WMS wms;
    private User user;
    private Users users;
    public void setWms(WMS wms) {this.wms = wms;}

    public void setUser(User user){
        this.user = user;
    }
    public void setUsers(Users users) {this.users = users;}



    public void initialize(){
        Platform.runLater(() -> {
            cardUserIDLabel.setText(cardUserIDLabel.getText() + user.getUserId());
            cardNameLabel.setText(cardNameLabel.getText() + user.getName());
            cardPremissionLabel.setText(cardPremissionLabel.getText() + user.getPermissions());



            editUserButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (wms.getSession().getUser().getUserId() == user.getUserId()){
                        System.out.println("Tooo bad can't edit yourself");
                    }
                    else {
                        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/userViews/userEditMenu.fxml")));
                        Stage stage = null;
                        try {
                            stage = loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        userDetailsController cont = loader.getController();

                        cont.setUser(user);
                        cont.setUsers(users);
                        stage.setTitle("UserID: " + user.getUserId());

                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                        stage.show();
                    }
                }
            });

        });
    }


}
