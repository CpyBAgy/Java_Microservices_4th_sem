package org.example;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.example.Notifications.OperationNotification;
import org.example.Notifications.PercentageNotification;

/**
 * The type Person.
 */
@Builder
@Getter
public class Person {

    private @NonNull String firstName;
    private @NonNull String secondName;
    @Builder.Default
    @Setter
    private String address = null;
    @Builder.Default
    @Setter
    private String passport = null;

    /**
     * Doubtful boolean.
     *
     * @return the boolean
     */
    public boolean doubtful() {

        return address == null && passport == null;
    }

    /**
     * Percentage notify.
     *
     * @param notification the notification
     * @param newPercent   the new percent
     */
    public void percentageNotify(PercentageNotification notification, Double newPercent) {
        System.out.println(notification + " " + newPercent);
    }

    /**
     * Operation notify.
     *
     * @param notification the notification
     */
    public void operationNotify(OperationNotification notification) {
        System.out.println(notification);
    }

    /**
     * Operation notify.
     *
     * @param notification     the notification
     * @param remainingBalance the remaining balance
     */
    public void operationNotify(OperationNotification notification, Double remainingBalance) {
        System.out.println(notification + " " + (remainingBalance != null ? remainingBalance : 0.0));
    }
}
