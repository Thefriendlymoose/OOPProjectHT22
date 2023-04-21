package model.finance.financeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void addDebit(TransactionRow debit){
        debits.add(debit);
    }

    public void addCredit(TransactionRow credit){
        credits.add(credit);
    }

    public double getDebitSum(){
        return debits.stream().mapToDouble(TransactionRow::getAmount).sum();
    }

    public double getCreditSum(){
        return credits.stream().mapToDouble(TransactionRow::getAmount).sum();
    }

    public boolean hasEqualSums(){
        return getCreditSum() == getDebitSum();
    }

    public Transaction getCopy(){
        return new Transaction(new ArrayList<>(debits), new ArrayList<>(credits));
    }

}
