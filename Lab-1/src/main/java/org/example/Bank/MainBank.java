package org.example.Bank;

import org.example.Account.IAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Main bank.
 */
public class MainBank {
    private final List<Transaction> transactions;

    /**
     * Instantiates a new Main bank.
     */
    public MainBank() {

        this.transactions = new ArrayList<>();
    }

    /**
     * Create transaction.
     *
     * @param fromBank    the from bank
     * @param fromAccount the from account
     * @param toBank      the to bank
     * @param toAccount   the to account
     * @param amount      the amount
     */
    public void createTransaction(IBank fromBank, IAccount fromAccount, IBank toBank, IAccount toAccount, Double amount) {
        Transaction transaction = new Transaction(fromBank, fromAccount, toBank, toAccount, amount);
        transactions.add(transaction);
        fromAccount.withdrawal(amount);
        toAccount.restocking(amount);
    }

    /**
     * Cancel transaction.
     *
     * @param transaction the transaction
     */
    public void cancelTransaction(Transaction transaction) {
        if (transactions.contains(transaction)) {
            transaction.getFromAccount().restocking(transaction.getAmount());
            transaction.getToAccount().withdrawal(transaction.getAmount());
            transactions.remove(transaction);
        }
    }
}
