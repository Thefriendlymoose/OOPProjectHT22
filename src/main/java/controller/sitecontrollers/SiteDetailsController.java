package controller.sitecontrollers;

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

/**
 * Controller for the view which is used show an existing site's details
 *
 * @author David Al Amiri
 */
public class SiteDetailsController implements Observer {
    @FXML
    private Label detailsTitleLabel;
    @FXML
    private TextField numberTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField maxCapacityTextField;

    @FXML
    private TextArea siteAddressTextArea;

    @FXML
    private VBox stockVBox, employeeVBox;

    private Site site;
    private WMS wms;

    public SiteDetailsController(WMS wms, Site site) {
        this.wms = wms;
        this.site = site;
        wms.registerObserver(this);
    }


    /**
     * Initializes the fields in the form with the data from the provided site.
     */
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

    /**
     * Loads the employees cards in a tab
     * @throws IOException throws exception if FXML fails to load
     */
    private void loadSiteEmployeeCards() throws IOException {
        employeeVBox.getChildren().clear();
        for(User user : site.getSiteUsers()){
            ParentDependencyInjection.addInjectionMethod(
                    SiteDetailsUserCardController.class, params -> new SiteDetailsUserCardController(wms, site, user)
            );

            Parent pane = ParentDependencyInjection.load("fxml/siteViews/siteDetailsUserCard.fxml");

            employeeVBox.getChildren().add(pane);
        }
    }

    /**
     * loads the sitearticle cards in a tab
     * @throws IOException throws exception if FXML fails to load
     */
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

    /**
     * Handles the event if the user clicks the edit button
     * @param e
     * @throws IOException throws exception if the fxml for the edit modal fails to load.
     */
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

    /**
     * Handles the event when a user clicks the close button
     * @param e
     */
    public void closeHandler(ActionEvent e) {
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
        wms.updateSite();
    }

    /**
     * Handles the event when a user clicks add stock item.
     * @param e
     * @throws IOException throws exception if the fxml fails to load
     */
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

    /**
     * Handles the event when a user clicks the add employee button.
     * @param e
     * @throws IOException throws exception if the fxml fails to load
     */
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
