package org.example.Bank;

import org.example.Account.AccountFactory.AccountFactory;
import org.example.Account.AccountType;
import org.example.Account.IAccount;
import org.example.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Standart bank.
 */
public class StandartBank implements IBank {
    /**
     * The Account list.
     */
    List<IAccount> accountList = new ArrayList<>();

    @Override
    public void recalculateRate(IAccount account, Double newPercent) {
        
        account.recalculateRate(newPercent);
    }

    @Override
    public void recalculateCommission(IAccount account, Double newCommission) {
        account.recalculateCommission(newCommission);
    }

    @Override
    public IAccount createNewAccount(Person person, AccountType accountType) {
        AccountFactory factory = new AccountFactory();
        return factory.createAccount(person, accountType);
    }
}
