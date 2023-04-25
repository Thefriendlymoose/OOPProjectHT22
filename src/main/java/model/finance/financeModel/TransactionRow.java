package model.finance.financeModel;


/**
 * Responsibility: Store the accountID and amount to be drawn from the account with the corresponding ID
 * Uses: -
 * Used by: Transaction, SiteFinanceController
 *
 * @author Simon Porsgaard / doktorjevsky
 * */

public class TransactionRow {
    private long accountID;
    private double amount;

    /**
     * @param accountID the ID of the account
     * @param amount the amount to be drawn / transferred from / to the account
     * @throws Exception if the amount is negative
     * */
    public TransactionRow(long accountID, double amount) throws Exception {
        if (amount < 0){
            throw new Exception("negative amount");
        }
        this.accountID = accountID;
        this.amount = amount;
    }

    public long getAccountID() {
        return accountID;
    }


    public double getAmount() {
        return amount;
    }


}

