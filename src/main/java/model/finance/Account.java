package model.finance;

/**
 * A class that represents a financial account
 * */
public class Account {

    private double balance;
    private long id;

    public Account(double balance, long id){
        this.balance = balance;
        this.id = id;
    }

    /**
     * Increases the balance by amount
     * */
    public void debit(double amount){
        this.balance += amount;
    }

    /**
     * Decreases the balance by amount
     * */
    public void credit(double amount){
        this.balance -= amount;
    }


    public double getBalance() {
        return balance;
    }

    public long getId(){
        return id;
    }
}
