package model.finance;

import model.finance.exceptions.IllegalTransactionException;

import java.util.ArrayList;
import java.util.List;

public class TransactionMaker {

    private final List<Booking> bookings = new ArrayList<>();

    public void addDebitBooking(Account account, double amount){
        bookings.add(new DebitBooking(account, amount));
    }

    public void addCreditBooking(Account account, double amount){
        bookings.add(new CreditBooking(account, amount));
    }

    public void makeTransactions() throws IllegalTransactionException {
        if (isValidTransaction()){
            for (Booking b : bookings){
                b.makeBooking();
            }
        } else {
            throw new IllegalTransactionException("Debit and credit must total to the same amount");
        }
    }

    private boolean isValidTransaction(){
        double s = 0;
        for (Booking b : bookings){
            s += b.getAmount();
        }
        return s == 0.0;
    }
}
