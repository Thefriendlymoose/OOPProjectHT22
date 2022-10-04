package controller.orderControllers;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import model.site.Site;


public class OrderSiteMenuCardController {

    @FXML
    private Label cardSiteNameLabel, cardNumberArticlesLabel, cardNumberEmployeesLabel;

    private Site site;

    public void setCard(Site site){
        this.site = site;
    }

    public void initialize(){
        Platform.runLater(() -> {
            cardSiteNameLabel.setText(cardSiteNameLabel.getText() + site.getSiteName());
            cardNumberArticlesLabel.setText(cardNumberArticlesLabel.getText() + site.getSiteArticles().size());
            cardNumberEmployeesLabel.setText(cardNumberEmployeesLabel.getText() + site.getEmployees().size());

        });
    }



}
