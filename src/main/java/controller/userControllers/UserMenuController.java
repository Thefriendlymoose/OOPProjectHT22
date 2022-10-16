package controller.userControllers;

import controller.dpi.DependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;
import persistence.IPersistence;
import model.user.User;
import persistence.UserDAO;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class UserMenuController {
    @FXML
    private Button openButton, createButton, listButton, backButton;
    private WMS wms;

    public UserMenuController(WMS wms) {
        this.wms = wms;
    }

    @FXML
    private VBox userCardHolder;


    private IPersistence<User> testDao = UserDAO.getInstance();

    List<User> users = testDao.getAll();

    public  void initialize() throws IOException {

        for (User user : users){
            FXMLLoader cardLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/userViews/userDetailsMenuCard.fxml")));
            AnchorPane pane = cardLoader.load();
            UserMenuCardController cont =  cardLoader.getController();

            cont.setCard(user);

            userCardHolder.getChildren().add(pane);
        }

    }





    public void backBtnHandler() throws Exception{
        Parent root = DependencyInjection.load("fxml/mainMenu.fxml");
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    public void createButton(ActionEvent e) throws Exception{
            Stage stage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/userViews/createUserMenu.fxml")));
            stage.setTitle("My modal window");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)e.getSource()).getScene().getWindow() );
            stage.show();}

    public void openButton(ActionEvent e) throws Exception{
        Stage stage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/userViews/openUser.fxml")));
        stage.setTitle("My modal window");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow() );
        stage.show();

    }
}
