package financetests;

import model.finance.financeModel.Transaction;
import model.finance.financeModel.TransactionRow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTests {

    private Transaction t1;

    @BeforeEach
    public void init(){
        t1 = new Transaction();
    }

    @Test
    public void copyTest() throws Exception {
        Transaction copy = t1.getCopy();
        t1.addCredit(new TransactionRow(1, 100));
        Assertions.assertEquals(0, copy.getDebits().size());
    }

    @Test
    public void checkSumTest1() throws Exception {
        t1.addCredit(new TransactionRow(1, 100));
        t1.addDebit(new TransactionRow(2, 100));
        Assertions.assertTrue(t1.hasEqualSums());
        Assertions.assertEquals(100, t1.getCreditSum());
    }

    @Test
    public void checkSumTest2() throws Exception {
        t1.addDebit(new TransactionRow(1, 100));
        t1.addCredit(new TransactionRow(2, 50));
        t1.addCredit(new TransactionRow(3, 50));
        Assertions.assertTrue(t1.hasEqualSums());
        Assertions.assertEquals(100, t1.getCreditSum());
    }
}
