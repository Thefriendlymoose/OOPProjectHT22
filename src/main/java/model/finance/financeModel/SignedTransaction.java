package model.finance.financeModel;

import model.user.User;

import java.time.LocalDate;

/**
 * Decorator class for Transaction
 *
 * */
public class SignedTransaction {

    private final Transaction transaction;
    private final User issuer;
    private final LocalDate signedAt;

    public SignedTransaction(Transaction transaction, User issuer, LocalDate date){
        this.transaction = transaction;
        this.issuer = issuer;
        this.signedAt = date;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public User getIssuer() {
        return issuer;
    }

    public LocalDate getSignedAt() {
        return signedAt;
    }
}
