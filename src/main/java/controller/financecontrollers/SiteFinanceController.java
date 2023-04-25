package controller.financecontrollers;

import controller.buttonEventHandlers.OpenFinanceMainController;
import controller.dpi.ParentDependencyInjection;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.WMS;
import model.finance.accounts.FinancialAccount;
import model.finance.financeModel.SiteFinanceModel;

import java.util.ArrayList;
import java.util.List;

public class SiteFinanceController {

    private WMS wms;
    private SiteFinanceModel siteFinanceModel;
    private BorderPaneController mainController;

    private Label leftMenuLabel;
    private Button makeTransactionButton;
    private Button backButton;

    private VBox centerBox;

    private ScrollPane centerPane;

    private List<Node> leftMenuNodes = new ArrayList<>();

    public SiteFinanceController(WMS wms, long siteID, BorderPaneController financeMainController) {
        this.wms = wms;
        this.mainController = financeMainController;
        try {
            siteFinanceModel = wms.getSiteFinanceModel(siteID);
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText(e.getMessage());
            error.showAndWait();
        }
    }

    public void initialize(){
        centerPane = new ScrollPane();
        leftMenuLabel = StyledComponentFactory.getStyledLabel("Site: " + wms.getSiteName(siteFinanceModel.getId()));
        makeTransactionButton = StyledComponentFactory.getStyledButton("New transaction");
        backButton = StyledComponentFactory.getStyledButton("Back");
        leftMenuNodes.add(leftMenuLabel);
        leftMenuNodes.add(makeTransactionButton);
        leftMenuNodes.add(backButton);
        centerBox = StyledComponentFactory.getStyledVBox();
        setMainButtonHandlers();
        mainController.loadLeft(leftMenuNodes);
        loadAccountCards();
    }

    private void setMainButtonHandlers(){
        backButton.setOnAction(new OpenFinanceMainController());
    }

    private void loadAccountCards(){
        List<FinancialAccount> accounts = siteFinanceModel.getAccounts();
        accounts.forEach(a -> {
            ParentDependencyInjection.addInjectionMethod(
                    FinancialAccountCardController.class, p -> new FinancialAccountCardController(a));
            Parent parent = null;
            try {
                parent = ParentDependencyInjection.load("fxml/financeViews/siteAccountCards.fxml");
            } catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Failed to load account: " + a.getName() + " with ID: " + a.getId());
                alert.showAndWait();
            }

            centerBox.getChildren().add(parent);
        });


        mainController.loadCenter(centerBox);

    }



}
