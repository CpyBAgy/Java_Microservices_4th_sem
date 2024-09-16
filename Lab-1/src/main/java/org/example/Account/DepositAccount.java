package org.example.Account;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.example.Notifications.OperationNotification;
import org.example.Notifications.PercentageNotification;
import org.example.Person;

import java.util.Objects;

/**
 * The type Deposit account.
 */
@Builder
@Getter
public class DepositAccount implements IAccount {
    private @NonNull Person person;
    private @NonNull Double balance;
    private @NonNull Double rate;
    private @NonNull Double commission;
    private Integer today;
    private Integer withdrawalDate;

    @Override
    public void registration(Person person) {
        this.person = person;
    }

    @Override
    public void withdrawal(Double amount) {
        if (Objects.equals(today, withdrawalDate)) {
            if (balance - amount >= 0) {
                balance -= amount;
                person.operationNotify(OperationNotification.SUCCESS_REMAINING_BALANCE, balance);
            } else {
                person.operationNotify(OperationNotification.NOT_ENOUGH_MONEY_REMAINING_BALANCE, balance);
            }
        } else {
            person.operationNotify(OperationNotification.NOT_POSSIBLE);
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

    public void recalculateCommission(Double newCommission) {
        commission = newCommission;
        person.percentageNotify(PercentageNotification.COMMISSION_CHANGED_TO, newCommission);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        DepositAccount that = (DepositAccount) obj;
        return Objects.equals(person, that.person) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(rate, that.rate) &&
                Objects.equals(today, that.today) &&
                Objects.equals(withdrawalDate, that.withdrawalDate);
    }
}
