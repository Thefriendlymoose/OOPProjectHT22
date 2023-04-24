package model.finance.accounts;


/**
 * Responsibility: Concrete implementation of one of the major types of accounts in bookkeeping.
 *                 It has the responsibility to hold and manipulate the account balance of a Liability Account
 * Used by: -
 * Uses: FinancialAccount
 *
 * @author Simon Porsgaard / doktorjevsky
 * */
public class CreditAccount extends FinancialAccount {

    public CreditAccount(String name, long id, double balance){
        super(name, id, balance);
    }

    public CreditAccount(String name, long id){
        super(name, id, 0);
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
        return new CreditAccount(getName(), getId(), balance);
    }
}
