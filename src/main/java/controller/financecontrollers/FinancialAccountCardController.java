package controller.financecontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.finance.accounts.FinancialAccount;

/**
 * Responsibility: A visual representation of a FinancialAccount
 * Uses: FinancialAccount, Label
 * Used by: SiteFinanceController
 *
 * @author Simon Porsgaard / doktorjevsky
 * */

public class FinancialAccountCardController {


    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label balanceLabel;

    private FinancialAccount acc;

    public FinancialAccountCardController(FinancialAccount acc){
        this.acc = acc;
    }

    public void initialize(){
        idLabel.setText(Long.toString(acc.getId()));
        nameLabel.setText(acc.getName());
        balanceLabel.setText(Double.toString(acc.getBalance()));
    }
}