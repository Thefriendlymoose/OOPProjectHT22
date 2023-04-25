package model.finance.financeModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A transaction is a set of debit and credit transactions, an integral part of
 * bookkeeping, where the sum of all debit bookings are equal all the credits.
 *
 *
 * Responsibility: Store entered by the user transaction rows
 * Uses: TransactionRow
 * Used by: SiteFinancialModel, SignedTransaction, SiteFinanceController
 *
 * @author Simon Porsgaard / doktorjevsky
 * */

public class Transaction {

    private List<TransactionRow> debits = new ArrayList<>();
    private List<TransactionRow> credits = new ArrayList<>();

    public Transaction(){}

    public Transaction(List<TransactionRow> debits, List<TransactionRow> credits){
        this.debits = debits;
        this.credits = credits;
    }

    public List<TransactionRow> getDebits(){
        return debits;
    }
    public List<TransactionRow> getCredits(){
        return credits;
    }

    /**
     * Mark the TransactionRow as a debit booking
     * @param debit
     * */
    public void addDebit(TransactionRow debit){
        debits.add(debit);
    }

    /**
     * Mark the TransactionRow as a credit booking
     * */
    public void addCredit(TransactionRow credit){
        credits.add(credit);
    }

    /**
     * @return sum of all debits
     * */
    public double getDebitSum(){
        return debits.stream().mapToDouble(TransactionRow::getAmount).sum();
    }

    /**
     * @return sum of all credits
     * */
    public double getCreditSum(){
        return credits.stream().mapToDouble(TransactionRow::getAmount).sum();
    }

    /**
     * @return true if the sum of all debits are equall to all credits
     * */
    public boolean hasEqualSums(){
        return getCreditSum() == getDebitSum();
    }

    public Transaction getCopy(){
        return new Transaction(new ArrayList<>(debits), new ArrayList<>(credits));
    }

}
