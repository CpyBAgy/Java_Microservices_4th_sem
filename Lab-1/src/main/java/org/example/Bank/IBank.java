package org.example.Bank;

import org.example.Account.IAccount;
import org.example.Account.AccountType;
import org.example.Person;

/**
 * The interface Bank.
 */
public interface IBank {
    /**
     * Recalculate rate.
     *
     * @param account    the account
     * @param newPercent the new percent
     */
    void recalculateRate(IAccount account, Double newPercent);

    /**
     * Recalculate commission.
     *
     * @param account       the account
     * @param newCommission the new commission
     */
    void recalculateCommission(IAccount account, Double newCommission);

    /**
     * Create new account account.
     *
     * @param person      the person
     * @param accountType the account type
     * @return the account
     */
    IAccount createNewAccount(Person person, AccountType accountType);
}
