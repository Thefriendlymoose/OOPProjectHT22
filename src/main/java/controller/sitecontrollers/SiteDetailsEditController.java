package controller.sitecontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.WMS;
import model.site.Site;
import model.site.Sites;

import java.util.Optional;

/**
 * Controller for the view which is for editing an existing site.
 *
 * @author David Al Amiri
 */
public class SiteDetailsEditController {

    @FXML
    private TextField numberTextField, nameTextField, maxCapacityTextField;

    @FXML
    private TextArea siteAddressTextArea;

    @FXML
    private Button saveButton, cancelButton;

    private Site site;
    private WMS wms;
    private Sites sites;

    public SiteDetailsEditController(WMS wms, Site site) {
        this.wms = wms;
        this.site = site;
        this.sites = wms.getSites();
    }

    public void initialize(){
        numberTextField.setText(Long.toString(sites.getNextId()));
        nameTextField.setText(site.getSiteName());
        maxCapacityTextField.setText(String.valueOf(site.getMaxCapacity()));
        maxCapacityTextField.setDisable(true);
        siteAddressTextArea.setText(site.getSiteAddress());
    }

    public void onSave(ActionEvent e){
        site.setSiteName(nameTextField.getText());
        site.setSiteAddress(siteAddressTextArea.getText());
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
        wms.updateSite();
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
