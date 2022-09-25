package controller.siteControllers;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;
import model.site.Site;
import model.site.SiteArticle;

import java.io.IOException;


public class SiteDetailsController {
    @FXML
    private Label detailsTitleLabel;

    @FXML
    private TextField numberTextField, nameTextField, maxCapacityTextField;

    @FXML
    private TextArea siteAddressTextArea;

    @FXML
    private Button editButton, closeButton;

    @FXML
    private VBox stockVBox, employeeVBox;

    private Site site;


    @FXML
    public void initialize(){

        Platform.runLater(() -> {
            detailsTitleLabel.setText(detailsTitleLabel.getText() + site.getSiteId());
            numberTextField.setText(String.valueOf(site.getSiteId()));
            nameTextField.setText(site.getSiteName());
            siteAddressTextArea.setText(site.getSiteAddress());
            maxCapacityTextField.setText(String.valueOf(site.getMaxCapacity()));

            for(User user : site.getEmployees()){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/siteViews/siteDetailsUserCard.fxml"));
                AnchorPane pane = null;
                try {
                    pane = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                SiteDetailsUserCardController controller = loader.getController();
                controller.setUser(user);

                employeeVBox.getChildren().add(pane);
            }

            for(SiteArticle sa : site.getSiteArticles()){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/siteViews/siteDetailsSiteArticleCard.fxml"));
                AnchorPane pane = null;

                try {
                    pane = loader.load();
                } catch(IOException e){
                    throw new RuntimeException(e);
                }

                SiteDetailsSiteArticleCardController controller = loader.getController();
                controller.setSiteArticle(sa);

                stockVBox.getChildren().add(pane);
            }

        });

    }

    public void setSite(Site site){
        this.site = site;
    }

    public void editHandler(ActionEvent e) throws IOException {


    }

    public void closeHandler(ActionEvent e) {
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
    }


}
