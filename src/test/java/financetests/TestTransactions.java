package financetests;

import model.finance.Account;
import model.finance.TransactionMaker;
import model.finance.exceptions.IllegalTransactionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTransactions {


    private Account a1;
    private Account a2;
    private Account a3;
    private TransactionMaker tm;

    @BeforeEach
    public void init(){
        a1 = new Account(1, 0);
        a2 = new Account(2, 0);
        a3 = new Account(3, 0);
        tm = new TransactionMaker();
    }

    @Test
    public void testValidTransaction(){
        double b1 = a1.getBalance();
        double b2 = a2.getBalance();
        double b3 = a3.getBalance();

        tm.addCreditBooking(a1, 100);
        tm.addDebitBooking(a2, 50);
        tm.addDebitBooking(a3, 50);

        Assertions.assertDoesNotThrow(() -> tm.makeTransactions());
        Assertions.assertEquals(b1 - 100, a1.getBalance());
        Assertions.assertEquals(b2 + 50, a2.getBalance());
        Assertions.assertEquals(b3 + 50, a3.getBalance());

    }

    @Test
    public void testInvalidTransaction(){
        tm.addDebitBooking(a1, 100);
        tm.addCreditBooking(a2, 20);

        Assertions.assertThrows(IllegalTransactionException.class, () -> tm.makeTransactions());
    }
}
