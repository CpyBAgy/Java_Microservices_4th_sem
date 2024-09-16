package org.example.Account;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.example.Notifications.OperationNotification;
import org.example.Notifications.PercentageNotification;
import org.example.Person;


/**
 * The type Debit account.
 */
@Builder
@Getter
public class DebitAccount implements IAccount {
    private @NonNull Person person;
    private @NonNull Double balance;
    private @NonNull Double rate;
    private @NonNull Double commission;

    @Override
    public void registration(Person person) {
        this.person = person;
    }

    public void withdrawal(Double amount) {
        if (balance - amount >= 0) {
            balance -= amount;
            person.operationNotify(OperationNotification.SUCCESS_REMAINING_BALANCE, balance);
        } else {
            person.operationNotify(OperationNotification.NOT_ENOUGH_MONEY_REMAINING_BALANCE, balance);
        }
    }

    @Override
    public void restocking(Double amount) {
        balance += amount;
        person.operationNotify(OperationNotification.SUCCESS_REMAINING_BALANCE, balance);
    }

    @Override
    public Double timeBooster(Integer months) {
        double startBalance = balance;
        for (int i = 0; i < months; i++) {
            balance += startBalance * (rate / 100);
        }
        return balance;
    }

    @Override
    public void recalculateRate(Double newRate) {
        rate = newRate;
        person.percentageNotify(PercentageNotification.RATE_CHANGED_TO, newRate);
    }

    @Override
    public void recalculateCommission(Double newCommission) {
        commission = newCommission;
        person.percentageNotify(PercentageNotification.COMMISSION_CHANGED_TO, newCommission);
    }
}
