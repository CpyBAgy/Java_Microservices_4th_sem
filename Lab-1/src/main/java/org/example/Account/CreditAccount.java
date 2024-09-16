package org.example.Account;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.example.Notifications.OperationNotification;
import org.example.Notifications.PercentageNotification;
import org.example.Person;

/**
 * The type Credit account.
 */
@Builder
@Getter
public class CreditAccount implements IAccount {
    private @NonNull Person person;
    private @NonNull Double balance;
    private @NonNull Double commission;
    private @NonNull Double limit;
    private Double rate;

    @Override
    public void registration(Person person) {
        this.person = person;
    }

    @Override
    public void withdrawal(Double amount) {
        if (balance - amount >= limit) {
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
        for (int i = 0; i < months; i++) {
            if (balance < 0) {
                balance *= (commission / 100);
            } else {
                return 0.0;
            }
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
