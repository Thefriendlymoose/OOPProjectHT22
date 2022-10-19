package controller.siteControllers;

import controller.dpi.ParentDependencyInjection;
import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;
import model.user.User;
import model.observer.Observer;
import model.site.Site;
import model.site.SiteArticle;

import java.io.IOException;


public class SiteDetailsController implements Observer {
    @FXML
    private Label detailsTitleLabel;

    @FXML
    private TextField numberTextField, nameTextField, maxCapacityTextField;

    @FXML
    private TextArea siteAddressTextArea;

    @FXML
    private Button editButton, closeButton, addStockButton, addEmployeeButton;

    @FXML
    private VBox stockVBox, employeeVBox;

    private Site site;
    private WMS wms;

    public SiteDetailsController(WMS wms, Site site) {
        this.wms = wms;
        this.site = site;
        wms.registerObserver(this);
    }


    @FXML
    public void initialize(){
        detailsTitleLabel.setText(detailsTitleLabel.getText() + site.getSiteId());
        numberTextField.setText(String.valueOf(site.getSiteId()));
        nameTextField.setText(site.getSiteName());
        siteAddressTextArea.setText(site.getSiteAddress());
        maxCapacityTextField.setText(String.valueOf(site.getMaxCapacity()));

        try {
            loadSiteArticleCards();
            loadSiteEmployeeCards();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadSiteEmployeeCards() throws IOException {
        employeeVBox.getChildren().clear();
        for(User user : site.getEmployees()){
            ParentDependencyInjection.addInjectionMethod(
                    SiteDetailsUserCardController.class, params -> new SiteDetailsUserCardController(wms, site, user)
            );

            Parent pane = ParentDependencyInjection.load("fxml/siteViews/siteDetailsUserCard.fxml");

            employeeVBox.getChildren().add(pane);
        }
    }
    private void loadSiteArticleCards() throws IOException {
        stockVBox.getChildren().clear();
        for(SiteArticle siteArticle : site.getSiteArticles()){
            ParentDependencyInjection.addInjectionMethod(
                    SiteDetailsSiteArticleCardController.class, params -> new SiteDetailsSiteArticleCardController(wms, site, siteArticle)
            );

            Parent pane = ParentDependencyInjection.load("fxml/siteViews/siteDetailsSiteArticleCard.fxml");

            stockVBox.getChildren().add(pane);
        }
    }

    public void editHandler(ActionEvent e) throws IOException {
        StageDependencyInjection.addInjectionMethod(
                SiteDetailsEditController.class, params -> new SiteDetailsEditController(wms, site)
        );

        Stage stage = StageDependencyInjection.load("fxml/siteViews/siteDetailsEditModal.fxml");

        stage.setTitle("Edit Site");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();

    }

    public void closeHandler(ActionEvent e) {
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
        wms.updateSite();
    }

    public void onStockAdd(ActionEvent e) throws IOException {
        StageDependencyInjection.addInjectionMethod(
                SiteDetailsSiteArticleAddModalController.class, params -> new SiteDetailsSiteArticleAddModalController(wms, site)
        );

        Stage stage = StageDependencyInjection.load("fxml/siteViews/siteDetailsSiteArticleAddModal.fxml");

        stage.setTitle("Add Stock Item");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();
    }

    public void onEmployeeAdd(ActionEvent e) throws IOException {
        StageDependencyInjection.addInjectionMethod(
                SiteDetailsUserAddModalController.class, params -> new SiteDetailsUserAddModalController(wms, site)
        );

        Stage stage = StageDependencyInjection.load("fxml/siteViews/siteDetailsUserAddModal.fxml");

        stage.setTitle("Add Employee");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();
    }

    @Override
    public void update() {
        try {
            loadSiteArticleCards();
            loadSiteEmployeeCards();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
