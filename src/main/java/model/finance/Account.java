package model.finance;

/**
 * A class that represents a fiscal account
 * */

public class Account {

    private final long id;
    private double balance;

    public Account(long id, double balance){
        this.id = id;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * @param amount the amount by which the account balance increases
     * */

    public void debit(double amount){ balance += amount; }

    /**
     * @param amount the amount by which the account balance decreases
     * */

    public void credit(double amount){ balance -= amount; }

}
