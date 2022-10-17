package controller.siteControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.site.Site;
import model.site.SiteArticle;
import model.site.Sites;

import java.util.Optional;

public class SiteDetailsSiteArticleEditModalController {

    @FXML
    private TextField articleNameTextField, amountTextField;

    @FXML
    private Button saveButton, cancelButton;

    private SiteArticle siteArticle;
    private Site site;
    private Sites sites;

    public SiteDetailsSiteArticleEditModalController(Sites sites, Site site, SiteArticle siteArticle) {
        this.sites = sites;
        this.site = site;
        this.siteArticle = siteArticle;
    }

    public void initialize(){
        articleNameTextField.setDisable(true);
        articleNameTextField.setText(siteArticle.getArticle().getArticleName());

        amountTextField.setText(String.valueOf(siteArticle.getAmount()));
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

    public void onSave(ActionEvent e){
        try{
            int amount = Integer.parseInt(amountTextField.getText());
            if (site.isOverCapacity(siteArticle, amount)){
                System.out.println("Over Capacity");
            } else {
                site.editSiteArticle(siteArticle, amount);
                ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
                sites.updateSite();
            }

        } catch (NumberFormatException error){
            System.out.println("Error only numbers accepted");
        }

    }

}
