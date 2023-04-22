package model.finance.accounts;


/**
 * Responsibility: Concrete implementation of one of the major types of accounts in bookkeeping.
 *                 It has the responsibility to hold and manipulate the account balance of a Liability Account
 * Used by: -
 * Uses: FinancialAccount
 *
 * @author Simon Porsgaard / doktorjevsky
 * */
public class LiabilityAccount extends FinancialAccount {

    public LiabilityAccount(String name, long id, double balance){
        super(name, id, balance);
    }

    /**
     * Liability Accounts get smaller on the debit side
     * */
    @Override
    public void debit(double amount) {
        balance -= amount;
    }

    /**
     * Liability Accounts grow on the credit side
     * */
    @Override
    public void credit(double amount) {
        balance += amount;
    }

    @Override
    public FinancialAccount getCopy() {
        return new LiabilityAccount(getName(), getId(), balance);
    }
}
