package controller.sitecontrollers;

import controller.dpi.ParentDependencyInjection;
import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;
import model.observer.Observer;
import model.site.Site;
import model.site.Sites;

import java.io.IOException;

/**
 * controller for the site menu view
 *
 * @author David Al Amiri
 */
public class SiteMenuController implements Observer {
    @FXML
    private Button backButton;

    @FXML
    private VBox siteCardHolderVBox;

    private Sites sites;
    private WMS wms;

    public SiteMenuController(WMS wms) {
        this.wms = wms;
        this.sites = wms.getSites();
        wms.registerObserver(this);
    }

    /**
     * Initializes the site menu.
     * Loads all the sitecards
     * @throws IOException
     */
    public void initialize() throws IOException {
        loadCards();
    }

    /**
     * Loads all the site into cards in the menu.
     *
     * @throws IOException Throws exception if the fxml fails to load
     */
    private void loadCards() throws IOException {
        siteCardHolderVBox.getChildren().clear();
        for (Site site : sites.getInList()){

            ParentDependencyInjection.addInjectionMethod(
                    SiteMenuSiteCardController.class, params -> new SiteMenuSiteCardController(wms, site)
            );

            Parent pane = ParentDependencyInjection.load("fxml/siteViews/siteMenuSiteCard.fxml");

            siteCardHolderVBox.getChildren().add(pane);
        }
    }

    /**
     * Handles the event when a user clicks the create button in the menu
     * Opens a modal with a form
     * @param e
     * @throws Exception Throws exception if the FXML fails to load
     */
    public void createButton(ActionEvent e) throws Exception{
        Stage stage = StageDependencyInjection.load("fxml/siteViews/siteCreateModal.fxml");

        stage.setTitle("Create Site");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) e.getSource()).getScene().getWindow());
        stage.show();
    }

    /**
     * Handles the event when a user clicks the back button in the menu
     * Returns the user back to the main menu
     * @throws Exception Throws exception if the FXML fails to load
     */
    public void backBtnHandler() throws Exception{
        Parent root = ParentDependencyInjection.load("fxml/mainMenu.fxml");

        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /**
     * Handles the event when a user clicks the open button in the menu
     * Opens a modal with a form where the user can open a site by entering the site id
     * @param e
     * @throws IOException Throws exception if the FXML fails to load
     */
    public void onOpen(ActionEvent e) throws IOException {
        Stage stage = StageDependencyInjection.load("fxml/siteViews/siteOpenDetailsModal.fxml");

        stage.setTitle("Open Site");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) e.getSource()).getScene().getWindow());
        stage.show();
    }

    @Override
    public void update() {
        try {
            loadCards();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
