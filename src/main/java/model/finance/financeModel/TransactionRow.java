package model.finance.financeModel;

import model.finance.accounts.FinancialAccount;

public record TransactionRow(long accountID, double amount) {
}
