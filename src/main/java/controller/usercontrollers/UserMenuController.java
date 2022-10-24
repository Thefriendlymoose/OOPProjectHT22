package controller.usercontrollers;

import controller.dpi.ParentDependencyInjection;
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
import model.user.strategySort.IStrategySort;
import model.user.strategySort.ascending.FirstNameSortAscending;
import model.user.strategySort.ascending.LastNameSortAscending;
import model.user.strategySort.ascending.UserIDSortAscending;
import model.user.strategySort.descending.FirstNameSortDescending;
import model.user.strategySort.descending.LastNameSortDescending;
import model.user.strategySort.descending.UserIDSortDescending;
import model.user.User;
import model.observer.Observer;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Controller for user menu
 */
public class UserMenuController implements Observer {
    @FXML
    private Button backButton;
    private WMS wms;
    private Users users;
    private IStrategySort sortUser = new UserIDSortDescending();

    public UserMenuController(WMS wms) {
        this.wms = wms;
        this.users = wms.getUsers();
        users.registerObserver(this);
    }

    @FXML
    private VBox userCardHolder;

    /**
     * initailizes the user cards
     * @throws IOException
     */
    public  void initialize() throws IOException {
        loadCards(sortUser);

    }


    /**
     * Loads the cards to user menu based on an order by the sort
     * @param strategySort the sort in which they will be ordered in
     * @throws IOException
     */
    private void loadCards(IStrategySort strategySort) throws IOException{
        userCardHolder.getChildren().clear();
        List<User> myUsers = users.getAllUsers();
        strategySort.sort(myUsers);
        for (User user : myUsers){
            FXMLLoader cardLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/userViews/userDetailsMenuCard.fxml")));
            AnchorPane pane = cardLoader.load();
            UserMenuCardController cont =  cardLoader.getController();

            cont.setUser(user);
            cont.setUsers(users);
            cont.setWms(wms);


            userCardHolder.getChildren().add(pane);
        }
    }


    /**
     * When user presses the back button
     * @throws Exception
     */
    public void backBtnHandler() throws Exception{
        Parent root = ParentDependencyInjection.load("fxml/mainMenu.fxml");
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }


    /**
     * When the user presses the create button
     * Transfers the user to create Controller
     * @param e when create button is pressed
     * @throws Exception
     */
    public void createButton(ActionEvent e) throws Exception{
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/userViews/CreateUserMenu.fxml")));
            Stage stage = loader.load();
            stage.setTitle("My modal window");
            CreateUserController controller = loader.getController();
            controller.setUsers(users);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)e.getSource()).getScene().getWindow() );
            stage.show();}

    /**
     * When the user presses the open button
     * transfers the user to open user details controller
     * @param e when open button is pressed
     * @throws Exception
     */
    public void openButton(ActionEvent e) throws Exception{

        Stage stage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/userViews/openUser.fxml")));
        stage.setTitle("Open User");

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow() );
        stage.show();





    }


    @Override
    public void update() {
        try {
            loadCards(sortUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //All the different sorts based

    /**
     * Handler for when userIDDown button is pressed
     * Sorts the users by userID
     * @param actionEvent when userIDDown button is pressed
     * @throws IOException
     */
    public void userIDDown(ActionEvent actionEvent) throws IOException {
        sortUser = new UserIDSortDescending();
        loadCards(sortUser);
    }
    /**
     * Handler for when userIDUp button is pressed
     * Sorts the users by userID
     * @param actionEvent when userIDDown button is pressed
     * @throws IOException
     */

    public void userIDUp(ActionEvent actionEvent) throws IOException {
        sortUser = new UserIDSortAscending();
        loadCards(sortUser);
    }
    /**
     * Handler for when sortFirstNameUp button is pressed
     * Sorts the users by FirstName
     * @param actionEvent when FirstNameUp button is pressed
     * @throws IOException
     */
    public void sortFirstNameUp(ActionEvent actionEvent) throws IOException {
        sortUser = new FirstNameSortAscending();
        loadCards(sortUser);
    }
    /**
     * Handler for when sortFirstNameDown button is pressed
     * Sorts the users by FirstName
     * @param actionEvent when FirstNameDown button is pressed
     * @throws IOException
     */

    public void sortFirstNameDown(ActionEvent actionEvent) throws IOException {
        sortUser = new FirstNameSortDescending();
        loadCards(sortUser);

    }
    /**
     * Handler for when sortLastNameUp button is pressed
     * Sorts the users by LastName
     * @param actionEvent when LastNameUp button is pressed
     * @throws IOException
     */

    public void sortLastNameUp(ActionEvent actionEvent) throws IOException {
        sortUser = new LastNameSortAscending();
        loadCards(sortUser);
    }
    /**
     * Handler for when sortLastNameDown button is pressed
     * Sorts the users by LastName
     * @param actionEvent when LastNameDown button is pressed
     * @throws IOException
     */

    public void sortLastNameDown(ActionEvent actionEvent) throws IOException {
        sortUser = new LastNameSortDescending();
        loadCards(sortUser);
    }
}
