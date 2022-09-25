package controller.siteControllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.site.SiteArticle;

public class SiteDetailsSiteArticleCardController {

    @FXML
    private Label cardNameLabel, cardAmountLabel;

    @FXML
    private Button cardGoToButton;

    private SiteArticle siteArticle;

    public void setSiteArticle(SiteArticle siteArticle){
        this.siteArticle = siteArticle;
    }

    public void initialize(){
        Platform.runLater(() -> {
            cardNameLabel.setText(cardNameLabel.getText() + siteArticle.getArticle());
            cardAmountLabel.setText(cardAmountLabel.getText() + siteArticle.getAmount());

            cardGoToButton.setOnAction(actionEvent -> {
                System.out.println("go to siteArticle");
            });
        });
    }

}
