package model.finance;

public class Booking {

    private final long accountId;
    private final double amount;

    public Booking(long accountId, double amount){
        this.accountId = accountId;
        this.amount = amount;
    }

    public long getAccountId(){
        return accountId;
    }

    public double getAmount(){
        return amount;
    }
}
