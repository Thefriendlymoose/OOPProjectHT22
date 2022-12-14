package controller.sitecontrollers;

import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;
import model.user.User;
import model.site.Site;
import model.site.SiteArticle;
import model.site.Sites;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Controller for the view which is used to create new sites.
 *
 * @author David Al Amiri
 */
public class SiteCreateController {

    @FXML
    private TextField numberTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField maxCapacityTextField;


    @FXML
    private TextArea siteAddressTextArea;

    private WMS wms;
    private Sites sites;

    public SiteCreateController(WMS wms) {
        this.wms = wms;
        this.sites = wms.getSites();
    }

    public void initialize(){
        numberTextField.setText(Long.toString(sites.getNextId()));
    }

    //TODO saving needs to be checked in the model
    public void onSave(ActionEvent e) throws IOException {
        Site newSite = new Site(sites.getNextId(), nameTextField.getText(), siteAddressTextArea.getText(),Integer.parseInt(maxCapacityTextField.getText()), new ArrayList<SiteArticle>(), new ArrayList<User>());

        wms.addSite(newSite);

        StageDependencyInjection.addInjectionMethod(
                SiteDetailsController.class, params -> new SiteDetailsController(wms, newSite)
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
