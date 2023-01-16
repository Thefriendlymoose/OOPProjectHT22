package financetests;

import model.finance.Account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAccount {

    private Account a1;

    @BeforeEach
    public void init(){
        a1 = new Account(1, 0);
    }

    @Test
    public void testDebit(){
        double before = a1.getBalance();
        double amount = 1;
        a1.debit(amount);
        Assertions.assertEquals(before + amount, a1.getBalance());
    }

    @Test
    public void testCredit(){
        double before = a1.getBalance();
        double amount = 1;
        a1.credit(amount);
        Assertions.assertEquals(before - amount, a1.getBalance());
    }
}
