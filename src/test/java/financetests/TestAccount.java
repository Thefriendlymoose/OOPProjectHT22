package financetests;

import model.finance.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAccount {

    private Account account;

    @BeforeEach
    public void init(){
        account = new Account(1, 0);
    }

    @Test
    public void testDebit(){
        double amount = 100.87;
        double before = account.getBalance();
        account.debit(amount);
        double after = account.getBalance();
        Assertions.assertEquals(before + amount, after);
    }

    @Test
    public void testCredit(){
        double amount = 100.92;
        double before = account.getBalance();
        account.credit(amount);
        double after = account.getBalance();
        Assertions.assertEquals(before - amount, after);
    }

    @Test
    public void testNotMalleable(){
        double before = account.getBalance();
        Account copy = account.getCopy();
        copy.debit(100);
        Assertions.assertEquals(before, account.getBalance());
    }

    @Test
    public void testNotMalleable2(){
        Account copy = account.getCopy();
        double before = copy.getBalance();
        account.debit(100);
        Assertions.assertEquals(before, copy.getBalance());
    }
}
