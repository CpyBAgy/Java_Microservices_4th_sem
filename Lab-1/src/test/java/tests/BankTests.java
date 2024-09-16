package tests;

import org.example.Account.AccountType;
import org.example.Account.DebitAccount;
import org.example.Account.IAccount;
import org.example.Person;
import org.example.Bank.IBank;
import org.example.Bank.MainBank;
import org.example.Bank.StandartBank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Bank tests.
 */
public class BankTests {
    /**
     * Test restock.
     */
    @Test
    public void testRestock() {
        Person person = Person.builder()
                .firstName("Иван")
                .secondName("Иванов")
                .build();

        DebitAccount account = DebitAccount.builder()
                .person(person)
                .balance(1000.0)
                .rate(1.0)
                .commission(0.01)
                .build();

        double restockAmount = 500.0;
        account.restocking(restockAmount);

        assertEquals(1500.0, account.getBalance());
    }

    /**
     * Test successful transaction.
     */
    @Test
    public void testSuccessfulTransaction() {
        Person person1 = Person.builder().firstName("Иван").secondName("Иванов").build();
        Person person2 = Person.builder().firstName("Петр").secondName("Попов").build();
        IBank tinkoff = new StandartBank();
        IBank sberbank = new StandartBank();
        IAccount accountIvan = tinkoff.createNewAccount(person1, AccountType.DEBIT);
        IAccount accountPetr = sberbank.createNewAccount(person2, AccountType.CREDIT);
        accountIvan.restocking(5000.0);
        accountPetr.restocking(3000.0);
        MainBank mainBank = new MainBank();
        mainBank.createTransaction(tinkoff, accountIvan, sberbank, accountPetr, 2000.0);
        assertEquals(3000.0, accountIvan.getBalance());
        assertEquals(5000.0, accountPetr.getBalance());
    }
}
