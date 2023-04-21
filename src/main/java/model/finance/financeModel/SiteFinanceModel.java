package model.finance.financeModel;

import model.finance.accounts.FinancialAccount;
import model.observer.Observable;
import model.observer.Observer;
import model.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class SiteFinanceModel implements Observable {

    private Map<Long, FinancialAccount> accounts;
    private transient List<Observer> observers = new ArrayList<>();
    private List<SignedTransaction> book;
    private final long SITE_ID;

    public SiteFinanceModel(long SITE_ID, Map<Long, FinancialAccount> accounts, List<SignedTransaction> book){
        this.SITE_ID = SITE_ID;
        this.accounts = accounts;
        this.book = book;
    }

    public long getId(){
        return SITE_ID;
    }

    public FinancialAccount getAccount(long id) throws Exception {
        if (!accounts.containsKey(id)){
            throw new Exception("Account doesn't exist");
        }
        return accounts.get(id);
    }

    public List<FinancialAccount> getAccounts(){
        return accounts
                .values()
                .stream()
                .map(FinancialAccount::getCopy)
                .collect(Collectors.toList());
    }

    public void addNewAccount(FinancialAccount account) throws Exception {
        if (accounts.containsKey(account.getId())){
            throw new Exception("Account already exists");
        }
        accounts.put(account.getId(), account);
        notifyObservers();
    }

    public void signAndMakeTransaction(Transaction transaction, User signer) throws Exception {
        if (!transaction.getDebits().stream().allMatch(t -> accounts.containsKey(t.getAccountID()))){
            throw new Exception("Account doesn't exist");
        }
        else if (!transaction.getCredits().stream().allMatch(t -> accounts.containsKey(t.getAccountID()))){
            throw new Exception("Account doesn't exist");
        }
        else if (!transaction.hasEqualSums()) {
            throw new Exception("Sums don't match.");
        }
        else if (transaction.getDebits().isEmpty() || transaction.getCredits().isEmpty()){
            throw new Exception("Transactions can't have empty rows");
        } else {
            transaction.getCredits().forEach(cr -> accounts.get(cr.getAccountID()).credit(cr.getAmount()));
            transaction.getDebits().forEach(db -> accounts.get(db.getAccountID()).debit(db.getAmount()));
            SignedTransaction signedTransaction = new SignedTransaction(transaction.getCopy(), signer, LocalDate.now());
            book.add(signedTransaction);
            notifyObservers();
        }
    }


    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void unregisterAll() {
        observers = new ArrayList<>();
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }
}
