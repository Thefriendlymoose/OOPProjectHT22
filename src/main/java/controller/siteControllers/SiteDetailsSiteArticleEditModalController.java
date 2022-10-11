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

    private SiteArticle sa;
    private Site site;
    private Sites sites;

    public void setSiteArticle(SiteArticle sa){
        this.sa = sa;
    }

    public void setSite(Site site){
        this.site = site;
    }


    public void initialize(){
        Platform.runLater(() -> {
            articleNameTextField.setDisable(true);
            articleNameTextField.setText(sa.getArticle().getArticleName());

            amountTextField.setText(String.valueOf(sa.getAmount()));
        });
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
            if (site.isOverCapacity(sa, amount)){
                System.out.println("Over Capacity");
            } else {
                site.editSiteArticle(sa, amount);
                ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
                sites.updateSite();
            }

        } catch (NumberFormatException error){
            System.out.println("Error only numbers accepted");
        }

    }

    public void setSites(Sites sites) {
        this.sites = sites;
    }
}
