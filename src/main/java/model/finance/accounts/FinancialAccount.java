package model.finance.accounts;

import java.util.Objects;

public abstract class FinancialAccount {

    private final String name;
    private final long id;
    protected double balance;

    public FinancialAccount(String name, long id, double balance){
        this.name = name;
        this.id = id;
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public abstract void debit(double amount);
    public abstract void credit(double amount);
    public abstract FinancialAccount getCopy();

    @Override
    public boolean equals(Object o){
        if(!(o instanceof FinancialAccount finAcc)){
            return false;
        }
        return id == finAcc.id;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

}
