package model.finance;

public interface FunctionalTransaction {

    void apply(Account acc, double amount);
}
