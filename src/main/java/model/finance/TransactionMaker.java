package model.finance;

import model.finance.exceptions.IllegalTransactionException;

import java.util.ArrayList;
import java.util.List;

public class TransactionMaker {

    private final List<Transaction> debits = new ArrayList<>();
    private final List<Transaction> credits = new ArrayList<>();



    /**
     * @param account the account to be debited
     * @param amount the amount with which the account will be debited
     * */

    public void addDebitBooking(Account account, double amount){
        debits.add(new Transaction(account, amount, Account::debit));

    }

    /**
     * @param account the account to be credited
     * @param amount the amount with which the account will be credited
     * */

    public void addCreditBooking(Account account, double amount){
        credits.add(new Transaction(account, amount, Account::credit));
    }

    /**
     * Will make all the added transactions IFF the total of all debit bookings are equal
     * to the total of all credit bookings.
     *
     * @throws IllegalTransactionException if the sum of debit is not equal to the sum of credit
     * */

    public void makeTransactions() throws IllegalTransactionException {
        if (isValidTransaction()){
            for (Transaction t : debits){
                t.makeTransaction();
            }
            for (Transaction t : credits){
                t.makeTransaction();
            }
        } else {
            throw new IllegalTransactionException("Debit and credit must total to the same amount");
        }
    }

    private boolean isValidTransaction(){
        double deb = 0;
        double cred = 0;
        for (Transaction t : debits){
            deb += t.getAmount();
        }
        for (Transaction t : credits){
            cred += t.amount;
        }
        return deb == cred;
    }

    private record Transaction(Account account, double amount, FunctionalTransaction f) {

        public double getAmount() {
            return amount;
        }

        public void makeTransaction() {
            f.apply(account, amount);
        }
    }
}
