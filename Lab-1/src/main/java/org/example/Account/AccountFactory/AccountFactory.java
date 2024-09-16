package org.example.Account.AccountFactory;

import org.example.Account.*;
import org.example.Person;

/**
 * The type Account factory.
 */
public class AccountFactory implements IAccountFactory {
    @Override
    public IAccount createAccount(Person person, AccountType type) {
        return switch (type) {
            case DEBIT -> DebitAccount.builder()
                    .person(person)
                    .balance(0.0)
                    .rate(0.0)
                    .commission(0.0)
                    .build();
            case CREDIT -> CreditAccount.builder()
                    .person(person)
                    .limit(0.0)
                    .commission(1.0)
                    .balance(0.0)
                    .build();
            case DEPOSIT -> DepositAccount.builder()
                    .person(person)
                    .balance(0.0)
                    .commission(0.0)
                    .build();
            default -> throw new IllegalArgumentException("Unknown account type: " + type);
        };
    }
}

