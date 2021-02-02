package ru.test.app.atm;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.test.app.exception.AtmAmountException;
import ru.test.app.processing.Bank;
import ru.test.app.processing.SberBank;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ATMTest {

    private static String pan;
    private static String pin;

    @BeforeAll
    public static void initAll(){
        pan = "1234567891234567";
        pin = "1234";
    }

    @Test
    void checkAccount() {
        CashDispenser cashDispenser = new CashDispenser(1000);
        Bank bank = new SberBank();

        ATM atm = new ATM(cashDispenser, bank);
        assertEquals(true, atm.checkAccount(pan, pin));
    }

    @Test
    @Disabled
    void getAtmMenu() {
    }

    @Test
    void getAccountBalance() {
        CashDispenser cashDispenser = new CashDispenser(1000);
        Bank bank = new SberBank();

        ATM atm = new ATM(cashDispenser, bank);
        Map<String, String> balance = atm.getAccountBalance(pan);
        assertEquals("100.0", balance.get("amount"));
        assertEquals("RUB", balance.get("currency"));
    }

    @Test
    void withdrawal() throws AtmAmountException{
        CashDispenser cashDispenser = new CashDispenser(1000);
        Bank bank = new SberBank();

        ATM atm = new ATM(cashDispenser , bank);
        atm.withdrawal(pan, 10);

        Map<String, String> balance = atm.getAccountBalance(pan);
        assertEquals("90.0", balance.get("amount"));
        assertEquals("RUB", balance.get("currency"));
    }

    @Test
    void deposit() {
        CashDispenser cashDispenser = new CashDispenser(1000);
        Bank bank = new SberBank();

        ATM atm = new ATM(cashDispenser , bank);
        atm.deposit(pan, 10);

        Map<String, String> balance = atm.getAccountBalance(pan);
        assertEquals("110.0", balance.get("amount"));
        assertEquals("RUB", balance.get("currency"));
    }

}