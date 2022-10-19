package controller.userControllers;

import controller.dpi.ParentDependencyInjection;
import controller.siteControllers.SiteOpenDetailsController;
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
import model.user.Users;
import persistence.IPersistence;
import model.user.User;
import persistence.UserDAO;
import model.observer.Observer;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

public class UserMenuController implements Observer {
    @FXML
    private Button openButton, createButton, listButton, backButton;
    private WMS wms;
    private Users users;

    public UserMenuController(WMS wms) {
        this.wms = wms;
        this.users = wms.getUsers();
        users.registerObserver(this);
    }

    @FXML
    private VBox userCardHolder;


    private IPersistence<User> testDao = UserDAO.getInstance();


    public  void initialize() throws IOException {
        loadCards();

    }


    private void loadCards() throws IOException{
        userCardHolder.getChildren().clear();
        for (User user : users.getInList()){
            FXMLLoader cardLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/userViews/userDetailsMenuCard.fxml")));
            AnchorPane pane = cardLoader.load();
            UserMenuCardController cont =  cardLoader.getController();

            cont.setUser(user);
            cont.setUsers(users);
            cont.setWms(wms);

            userCardHolder.getChildren().add(pane);
        }
    }





    public void backBtnHandler() throws Exception{
        Parent root = ParentDependencyInjection.load("fxml/mainMenu.fxml");
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    public void createButton(ActionEvent e) throws Exception{
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/userViews/CreateUserMenu.fxml")));
            Stage stage = loader.load();
            stage.setTitle("My modal window");
            CreateUserController controller = loader.getController();
            controller.setUsers(users);
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

    @Override
    public void update() {
        try {
            loadCards();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
