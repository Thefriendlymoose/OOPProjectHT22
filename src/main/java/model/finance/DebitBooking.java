package model.finance;

public class DebitBooking implements Booking{

    private final Account account;
    private final double amount;

    public DebitBooking(Account account, double amount){
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void makeBooking() {
        account.debit(amount);
    }

    @Override
    public double getAmount() {
        return amount;
    }
}
