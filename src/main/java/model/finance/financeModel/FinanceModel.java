package model.finance.financeModel;

import model.finance.accounts.FinancialAccount;
import model.observer.Observable;
import model.observer.Observer;
import model.site.Site;
import model.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FinanceModel implements Observable {

    private Map<Long, FinancialAccount> accounts;
    private List<Observer> observers = new ArrayList<>();
    private List<SignedTransaction> book;
    private Site site;
    private User issuer;

    public FinanceModel(Map<Long, FinancialAccount> accounts, List<SignedTransaction> book, User issuer, Site site){
        this.accounts = accounts;
        this.site = site;
        this.book = book;
        this.issuer = issuer;
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

    public void signAndMakeTransaction(Transaction transaction) throws Exception {
        if (!transaction.getDebits().stream().allMatch(t -> accounts.containsKey(t.accountID()))){
            throw new Exception("Account doesn't exist");
        }
        else if (!transaction.getCredits().stream().allMatch(t -> accounts.containsKey(t.accountID()))){
            throw new Exception("Account doesn't exist");
        }
        else if (!transaction.hasEqualSums()) {
            throw new Exception("Sums don't match.");
        } else {
            transaction.getCredits().forEach(cr -> accounts.get(cr.accountID()).credit(cr.amount()));
            transaction.getDebits().forEach(db -> accounts.get(db.accountID()).debit(db.amount()));
            SignedTransaction signedTransaction = new SignedTransaction(transaction.getCopy(), issuer, LocalDate.now());
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
