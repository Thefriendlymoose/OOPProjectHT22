package controller.siteControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.site.Site;
import model.site.Sites;
import persistence.SitesDAO;

import java.util.Optional;

public class SiteDetailsEditController {

    @FXML
    private TextField numberTextField, nameTextField, maxCapacityTextField;

    @FXML
    private TextArea siteAddressTextArea;

    @FXML
    private Button saveButton, cancelButton;


    private Site site;
    private Sites sites;

    public void setSite(Site site){
        this.site = site;
    }

    public void initialize(){
        Platform.runLater(() -> {

            numberTextField.setText(Integer.toString((int) SitesDAO.getInstance().getNextId()));
            nameTextField.setText(site.getSiteName());
            maxCapacityTextField.setText(String.valueOf(site.getMaxCapacity()));
            maxCapacityTextField.setDisable(true);
            siteAddressTextArea.setText(site.getSiteAddress());

        });
    }

    public void onSave(ActionEvent e){
        site.setSiteName(nameTextField.getText());
        site.setSiteAddress(siteAddressTextArea.getText());
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
        sites.updateSite();
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
