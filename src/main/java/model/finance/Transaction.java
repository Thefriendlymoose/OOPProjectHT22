package model.finance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private List<Booking> debits = new ArrayList<>();
    private List<Booking> credits = new ArrayList<>();
    private LocalDateTime timestamp;

    public Transaction(){
        timestamp = LocalDateTime.now();
    }

    public void addDebitBooking(Booking b){
        debits.add(b);
    }

    public void addDebitBooking(long accountId, double amount){
        Booking b = new Booking(accountId, amount);
        addDebitBooking(b);
    }

    public void addCreditBooking(Booking b){
        credits.add(b);
    }

    public void addCreditBooking(long accountId, double amount){
        Booking b = new Booking(accountId, amount);
        addCreditBooking(b);
    }
}
