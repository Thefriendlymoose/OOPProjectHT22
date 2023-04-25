package financetests;

import model.finance.accounts.DebitAccount;
import model.finance.accounts.FinancialAccount;
import model.finance.accounts.CreditAccount;
import model.finance.financeModel.SignedTransaction;
import model.finance.financeModel.SiteFinanceModel;
import model.finance.financeModel.Transaction;
import model.finance.financeModel.TransactionRow;
import model.user.Role;
import model.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.IPersistence;
import persistence.dataaccessobjects.FinanceModelDAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

public class FinanceDaoTest {

    private IPersistence<SiteFinanceModel> dao;
    private List<SiteFinanceModel> l1;
    private Map<Long, FinancialAccount> a1;
    private List<SignedTransaction> book;
    private String path = "src/test/testResources/testfinance.json";

    @BeforeEach
    public void init() throws Exception {
        dao = new FinanceModelDAO(path);
        a1 = new HashMap<>();
        a1.put(1L, new CreditAccount("liabilities", 1, 0));
        a1.put(2L, new DebitAccount("assets", 2, 0));
        book = List.of(new SignedTransaction(
                new Transaction(List.of(new TransactionRow(1, 100)), List.of(new TransactionRow(2,100))),
                new User(1,"name", "a", "namee", true, Role.getAdmin()),
                LocalDate.now()));
        l1 = List.of(new SiteFinanceModel(1, a1, book));
    }

    @Test
    public void saveTest(){
        dao.save(l1);
        Map<Long, SiteFinanceModel> map = dao.getAllMap();
        Assertions.assertTrue(l1.stream().allMatch(e -> map.containsKey(e.getId())));
        Assertions.assertTrue(l1
                .stream()
                .allMatch(sf -> map.get(sf.getId()).getAccounts().equals(sf.getAccounts()))
        );
        Assertions.assertTrue(l1.stream().allMatch(sf -> map.get(sf.getId()).getAccounts().equals(sf.getAccounts())));


    }
    @Test
    public void saveTest2() throws IOException {
        dao.save(l1);
        String res = new String(Files.readAllBytes(Path.of(path)));
        Map<Long, SiteFinanceModel> map = dao.getAllMap();
        dao.save(map.values().stream().toList());
        String res2 = new String(Files.readAllBytes(Path.of(path)));
        Assertions.assertEquals(res, res2);

    }
}
