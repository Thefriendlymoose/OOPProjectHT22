package controller.siteControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;
import model.site.Site;
import model.site.Sites;
import persistence.UserDAO;

import java.util.Optional;

public class siteDetailsUserAddModalController {

    @FXML
    private ListView<User> chooseUserListView;

    @FXML
    private Button saveButton, CancelButton;

    private User current;

    private Site site;
    private Sites sites;

    public void setSite(Site site){
        this.site = site;
    }

    public void initialize(){
        Platform.runLater(() -> {
            chooseUserListView.getItems().addAll(UserDAO.getInstance().getAll());

            chooseUserListView.setCellFactory(param -> new ListCell<User>(){
                @Override
                protected void updateItem(User s, boolean empty){
                    super.updateItem(s, empty);

                    if(empty || s == null || s.getName() == null){
                        setText(null);
                    } else {
                        setText(s.getName());
                    }
                }
            });


            chooseUserListView.getSelectionModel().selectedItemProperty().addListener((observableValue, site, t1) -> {
                current = chooseUserListView.getSelectionModel().getSelectedItem();
            });
        });
    }

    public void onSave(ActionEvent e){
        if (site.checkEmployeeInSite(current)){
            System.out.println("Employee already in site");
        } else {
            site.addEmployee(current);
            ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
            sites.updateSite();
        }
    }

    public void onCancel(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("All changes made will be cancelled");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL){
            System.out.println("Clicked Cancel");
        } else {
            ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
        }
    }


    public void setSites(Sites sites) {
        this.sites = sites;
    }
}
