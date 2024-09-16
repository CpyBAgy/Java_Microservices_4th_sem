package org.example.Bank;

import lombok.Getter;
import org.example.Account.IAccount;

/**
 * The type Transaction.
 */
@Getter
public class Transaction {
    private final IBank fromBank;
    private final IAccount fromAccount;
    private final IBank toBank;
    private final IAccount toAccount;
    private final double amount;

    /**
     * Instantiates a new Transaction.
     *
     * @param fromBank    the from bank
     * @param fromAccount the from account
     * @param toBank      the to bank
     * @param toAccount   the to account
     * @param amount      the amount
     */
    public Transaction(IBank fromBank, IAccount fromAccount, IBank toBank, IAccount toAccount, double amount) {
        this.fromBank = fromBank;
        this.fromAccount = fromAccount;
        this.toBank = toBank;
        this.toAccount = toAccount;
        this.amount = amount;
    }
}
