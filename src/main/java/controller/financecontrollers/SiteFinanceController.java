package controller.financecontrollers;

import controller.buttonEventHandlers.OpenFinanceMainController;
import controller.dpi.ParentDependencyInjection;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.WMS;
import model.finance.accounts.FinancialAccount;
import model.finance.financeModel.SiteFinanceModel;
import model.finance.financeModel.Transaction;
import model.finance.financeModel.TransactionRow;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SiteFinanceController {

    private WMS wms;
    private SiteFinanceModel siteFinanceModel;
    private BorderPaneController mainController;

    private Label leftMenuLabel;

    private Button makeTransactionButton;
    private Button backButton;
    //private Button addRowButton;

    private VBox centerBox;
    private GridPane transactionPane;

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
        transactionPane = new GridPane();
        leftMenuLabel = StyledComponentFactory.getStyledLabel("Site: " + wms.getSiteName(siteFinanceModel.getId()));
        makeTransactionButton = StyledComponentFactory.getStyledButton();
        backButton = StyledComponentFactory.getStyledButton();
        leftMenuNodes.add(leftMenuLabel);
        leftMenuNodes.add(makeTransactionButton);
        leftMenuNodes.add(backButton);
        centerBox = StyledComponentFactory.getStyledVBox();
        setMainButtons();
        mainController.loadLeft(leftMenuNodes);
        loadAccountCards();
    }

    private void setMainButtons(){
        backButton.setText("Back");
        makeTransactionButton.setText("New transaction");
        backButton.setOnAction(new OpenFinanceMainController());
        makeTransactionButton.setOnAction(this::makeTransactionHandler);
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

    // cols: 0 := choice; 1 := debit amount; 2 := credit amount
    // row 0 := LABELS
    private void loadTransactionPane(){
        transactionPane.getChildren().clear();
        transactionPane.add(StyledComponentFactory.getStyledLabel("Account"),0,0);
        transactionPane.add(StyledComponentFactory.getStyledLabel("Debit"), 1, 0);
        transactionPane.add(StyledComponentFactory.getStyledLabel("Credit"),2,0);
        IntStream.range(0,3).forEach(i -> {
            addRowToTransactionPane();
        });
    }

    private void addRowToTransactionPane(){
        int row = transactionPane.getRowCount();
        transactionPane.add(getAccountChoices(), 0, row);
        transactionPane.add(new TextField(), 1, row);
        transactionPane.add(new TextField(), 2, row);
    }

    private void removeRowFromTransactionPane(){
        int row = transactionPane.getRowCount();
        int col = transactionPane.getColumnCount();
        if (row <= 2){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("There are no more rows to remove!");
            alert.showAndWait();
        } else {
            IntStream.range(1,col + 1).forEach(i -> transactionPane.getChildren().remove(row * col - i));
        }
    }

    public void makeTransactionHandler(ActionEvent e){
        loadTransactionPane();
        // create buttons
        Button addRowButton = StyledComponentFactory.getStyledButton("Add transaction row");
        addRowButton.setOnAction(ae -> addRowToTransactionPane());
        Button removeRow = StyledComponentFactory.getStyledButton("Remove last row");
        removeRow.setOnAction(ae -> removeRowFromTransactionPane());

        // put everything in containers for that slick look
        VBox b = new VBox();
        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(addRowButton);
        buttonBox.getChildren().add(removeRow);
        b.getChildren().add(transactionPane);
        b.getChildren().add(buttonBox);
        mainController.loadCenter(b);

        // change behavior of makeTransactionButton
        makeTransactionButton.setText("Confirm");
        makeTransactionButton.setOnAction(this::confirmTransaction);
        // change behavior of backButton
        backButton.setOnAction(ae -> {
            setMainButtons();
            loadAccountCards();
        });
    }

    public void confirmTransaction(ActionEvent ae){
        try {
            Transaction t = parseGridPane();
            siteFinanceModel.signAndMakeTransaction(t, wms.getSession().getUser());
            setMainButtons();
            loadAccountCards();

        } catch (Exception e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(e.getMessage());
            a.showAndWait();
        }
    }

    private Transaction parseGridPane() throws Exception{
        Transaction t = new Transaction();
        int rows = transactionPane.getRowCount();
        int i = 3;
        List<Node> nodes = transactionPane.getChildren();
        // x % 3 = 0 ==> account, x % 3 = 1 ==> debit amount, x % 3 = 2 ==> credit amount
        while (i < rows * 3){
            FinancialAccount acc = ((ChoiceBox<FinancialAccount>) nodes.get(i)).getValue();
            String debitString = ((TextField) nodes.get(i+1)).getText();
            String creditString = ((TextField) nodes.get(i+2)).getText();
            if (acc != null) {
                if (!debitString.equals("")){
                    TransactionRow tr = new TransactionRow(acc.getId(), Double.parseDouble(debitString));
                    t.addDebit(tr);
                }
                if (!creditString.equals("")) {
                    TransactionRow tr = new TransactionRow(acc.getId(), Double.parseDouble(creditString));
                    t.addCredit(tr);
                }
            }
            else if (!debitString.equals("") || !creditString.equals("")){
                throw new Exception("Choose an account");
            }
            i += 3;
        }
        return t;
    }

    private ChoiceBox<FinancialAccount> getAccountChoices(){
        ChoiceBox<FinancialAccount> accs = new ChoiceBox<>();
        accs.getItems().addAll(siteFinanceModel.getAccounts());
        return accs;
    }




}
