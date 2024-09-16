package org.example.Account.AccountFactory;

import org.example.Account.AccountType;
import org.example.Account.IAccount;
import org.example.Person;

/**
 * The interface Account factory.
 */
public interface IAccountFactory {
    /**
     * Create account account.
     *
     * @param person the person
     * @param type   the type
     * @return the account
     */
    IAccount createAccount(Person person, AccountType type);
}
