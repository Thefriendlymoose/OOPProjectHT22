package model.finance.accounts;

public class AssetAccount extends FinancialAccount {

    public AssetAccount(String name, long id, double balance){
        super(name, id, balance);

    }

    @Override
    public void debit(double amount) {
        balance += amount;
    }

    @Override
    public void credit(double amount) {
        balance -= amount;
    }

    @Override
    public FinancialAccount getCopy() {
        return new AssetAccount(getName(), getId(), balance);
    }
}
