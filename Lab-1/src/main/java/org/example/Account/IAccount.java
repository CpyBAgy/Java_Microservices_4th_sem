package org.example.Account;

import org.example.Person;

/**
 * The interface Account.
 */
public interface IAccount {
    /**
     * Registration.
     *
     * @param person the person
     */
    void registration(Person person);

    /**
     * Withdrawal.
     *
     * @param amount the amount
     */
    void withdrawal(Double amount);

    /**
     * Restocking.
     *
     * @param amount the amount
     */
    void restocking(Double amount);

    /**
     * Time booster double.
     *
     * @param months the months
     * @return the double
     */
    Double timeBooster(Integer months);

    /**
     * Recalculate rate.
     *
     * @param newPercent the new percent
     */
    void recalculateRate(Double newPercent);

    /**
     * Recalculate commission.
     *
     * @param newCommission the new commission
     */
    void recalculateCommission(Double newCommission);

    /**
     * Gets balance.
     *
     * @return the balance
     */
    Double getBalance();
}
