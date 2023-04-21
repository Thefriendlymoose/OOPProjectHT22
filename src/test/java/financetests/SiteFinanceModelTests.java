package financetests;

import model.finance.accounts.AssetAccount;
import model.finance.accounts.FinancialAccount;
import model.finance.accounts.LiabilityAccount;
import model.finance.financeModel.SiteFinanceModel;
import model.finance.financeModel.Transaction;
import model.finance.financeModel.TransactionRow;
import model.user.Role;
import model.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class SiteFinanceModelTests {

    private SiteFinanceModel m1;
    private SiteFinanceModel m2;
    private Transaction nullTransaction;
    private Transaction t1;
    private Transaction t2;
    private User issuer;

    @BeforeEach
    public void init(){
        issuer = new User(1, "baby", "abc", "Simon", true, Role.getAdmin());
        m1 = new SiteFinanceModel(1,new HashMap<>(), new ArrayList<>());
        Map<Long, FinancialAccount> map2 = new HashMap<>();
        map2.put(1L, new AssetAccount("Assets", 1, 0));
        m2 = new SiteFinanceModel(1, map2, new ArrayList<>());
        nullTransaction = new Transaction();
        t1 = new Transaction(List.of(new TransactionRow(2, 100)), List.of(new TransactionRow(1, 100)));
        t2 = new Transaction(List.of(new TransactionRow(2, 50)), List.of(new TransactionRow(1, 100)));
    }

    @Test
    public void createAccountTest(){
        Assertions.assertThrows(Exception.class, () -> m1.getAccount(1));
        Assertions.assertDoesNotThrow(() -> {
            m1.addNewAccount(new LiabilityAccount("Debt", 2, 0));
            FinancialAccount acc = m1.getAccount(2);
            Assertions.assertEquals(acc, new LiabilityAccount("Debt", 2, 0));
        });
    }

    @Test
    public void assertImmutabilityOfAccountsTest(){
        List<FinancialAccount> accs = m2.getAccounts();
        accs.forEach(a -> a.debit(1000));
        Assertions.assertFalse(IntStream
                .range(0, accs.size())
                .anyMatch(i -> accs.get(i).getBalance() == m2.getAccounts().get(i).getBalance()));
    }

    @Test
    public void accountDoesntExistTest(){
        Assertions.assertDoesNotThrow(() -> m1.addNewAccount(new LiabilityAccount("", 1, 100)));
        Assertions.assertThrows(Exception.class, () -> {
            m1.signAndMakeTransaction(t1, issuer);
        });
    }

    @Test
    public void accountDoesntExistTest2(){
        Assertions.assertDoesNotThrow(() -> m1.addNewAccount(new LiabilityAccount("", 2, 100)));
        Assertions.assertThrows(Exception.class, () -> {
            m1.signAndMakeTransaction(t1, issuer);
        });
    }
    @Test
    public void nullTransactionTest(){
        Assertions.assertThrows(Exception.class, () -> {
            m1.signAndMakeTransaction(nullTransaction, issuer);
        });
    }

    @Test
    public void amountsDontMatchTest(){
        Assertions.assertDoesNotThrow(() -> m2.addNewAccount(new AssetAccount("Asset", 2, 0)));
        Assertions.assertThrows(Exception.class, () -> m2.signAndMakeTransaction(t2, issuer));
    }


}
