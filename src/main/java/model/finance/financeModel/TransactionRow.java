package model.finance.financeModel;

import model.finance.accounts.FinancialAccount;

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

