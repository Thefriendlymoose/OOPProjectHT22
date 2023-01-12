package model.finance;



public class CreditBooking implements Booking {

    private final Account account;
    private final double amount;

    public CreditBooking(Account account, double amount){
        this.account = account;
        this.amount = amount;

    }

    @Override
    public void makeBooking() {
        account.credit(amount);
    }

    @Override
    public double getAmount() {
        return -amount;
    }
}
