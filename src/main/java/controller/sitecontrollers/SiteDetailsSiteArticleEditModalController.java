package controller.sitecontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.WMS;
import model.site.Site;
import model.site.SiteArticle;

import java.util.Optional;

/**
 * Controller for the view which is for editing an existing site article
 *
 * @author David Al Amiri
 */
public class SiteDetailsSiteArticleEditModalController {

    @FXML
    private TextField articleNameTextField;

    @FXML
    private TextField amountTextField;

    private SiteArticle siteArticle;
    private Site site;
    private WMS wms;

    public SiteDetailsSiteArticleEditModalController(WMS wms, Site site, SiteArticle siteArticle) {
        this.wms = wms;
        this.site = site;
        this.siteArticle = siteArticle;
    }

    /**
     * Initializes fields in the modal with data from the sitearticle
     */
    public void initialize(){
        articleNameTextField.setDisable(true);
        articleNameTextField.setText(siteArticle.getArticle().getArticleName());

        amountTextField.setText(String.valueOf(siteArticle.getAmount()));
    }

    /**
     * Handles the event when a user clicks the cancel button in the modal
     * Opens a new modal where the user will have to confirm whether to cancel or return
     * @param e
     */
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

    /**
     * Handles the save button when a user clicks save in the modal
     * Parses the string that is returned from the TextField, if it fails throws NumberFormat
     * @param e
     */
    public void onSave(ActionEvent e){
        try{
            int amount = Integer.parseInt(amountTextField.getText());
            if (site.editSiteArticle(siteArticle, amount)){
                site.editSiteArticle(siteArticle, amount);
                ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
                wms.updateSite();
            } else {
                //TODO add error popup
                System.out.println("Over Capacity");
            }

        } catch (NumberFormatException error){
            //TODO add error popup
            System.out.println("Error only numbers accepted");
        }

    }

}
