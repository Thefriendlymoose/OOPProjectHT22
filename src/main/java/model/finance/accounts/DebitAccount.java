package model.finance.accounts;

/**
 * Responsibility: Concrete implementation of one of the major types of accounts in bookkeeping.
 *                 It has the responsibility to hold and manipulate the account balance of an Asset Account
 * Used by: -
 * Uses: FinancialAccount
 *
 * @author Simon Porsgaard / doktorjevsky
 * */
public class DebitAccount extends FinancialAccount {

    public DebitAccount(String name, long id, double balance){
        super(name, id, balance);

    }

    public DebitAccount(String name, long id){
        super(name, id, 0);
    }

    /**
     * AssetAccounts grow on the debit side
     * */
    @Override
    public void debit(double amount) {
        balance += amount;
    }

    /**
     * AssetAccounts shrink on the credit side
     * */
    @Override
    public void credit(double amount) {
        balance -= amount;
    }

    @Override
    public FinancialAccount getCopy() {
        return new DebitAccount(getName(), getId(), balance);
    }
}
