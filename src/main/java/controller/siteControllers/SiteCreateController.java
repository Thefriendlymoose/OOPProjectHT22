package controller.siteControllers;

import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.user.User;
import model.site.Site;
import model.site.SiteArticle;
import model.site.Sites;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class SiteCreateController {

    @FXML
    private TextField numberTextField, nameTextField, maxCapacityTextField;

    @FXML
    private TextArea siteAddressTextArea;

    @FXML
    private Button saveButton, cancelButton;
    private Sites sites;

    public SiteCreateController(Sites sites) {
        this.sites = sites;
    }

    public void initialize(){
        numberTextField.setText(Long.toString(sites.getNextId()));
    }

    public void onSave(ActionEvent e) throws IOException {
        System.out.println("Clicked save");
        Site newSite = new Site(sites.getNextId(), nameTextField.getText(), siteAddressTextArea.getText(),Integer.parseInt(maxCapacityTextField.getText()), new ArrayList<SiteArticle>(), new ArrayList<User>());

        sites.addSite(newSite);

        StageDependencyInjection.addInjectionMethod(
                SiteDetailsController.class, params -> new SiteDetailsController(sites, newSite)
        );

        Stage stage = StageDependencyInjection.load("fxml/siteViews/siteDetailsModal.fxml");
        stage.setTitle("Site: " + newSite.getSiteId());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()).getOwner());
        stage.show();
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();

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

}
