package model.finance.financeModel;


/**
 * Responsibility: Store the accountID and amount to be drawn from the account with the corresponding ID
 * Uses: -
 * Used by: Transaction
 *
 * @author Simon Porsgaard / doktorjevsky
 * */

public class TransactionRow {
    private long accountID;
    private double amount;


    public TransactionRow(long accountID, double amount) {
        this.accountID = accountID;
        this.amount = amount;
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}

