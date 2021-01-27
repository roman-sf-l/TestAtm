package ru.test.app.atm;

import ru.test.app.exception.AtmAmountException;
import ru.test.app.processing.Bank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class ATM {

    private Bank bank = new Bank();
    private CashDispenser cashDispenser;
    private BiPredicate<String, String> isPanPinCorrect = (pan, pin) -> pan.matches("^\\d{16,}$") && pin.matches("^\\d{4}$");

    public ATM(CashDispenser cashDispenser) {
        this.cashDispenser = cashDispenser;
    }

    public boolean checkAccount(String account, String pin){
        return isPanPinCorrect.test(account, pin) && bank.isAccountCorrect(account, pin);
    }

    public String getAtmMenu(){
        StringBuilder menu = new StringBuilder();
        Arrays.stream(Actions.values()).forEach(action -> menu.append(action.ordinal() + 1 + ". " + action.toString() + "\n"));
        return menu.toString();
    }

    public Map<String, String> getAccountBalance(String account){
        Map<String, String> balance = new HashMap<>(2);
        balance.put("amount", bank.getAccountBalance(account).toString());
        balance.put("currency", bank.getAccountCurrency(account).toString());
        return balance;
    }

    public void withdrawal(String account, double  amount) throws AtmAmountException{
        double accountBalance = bank.getAccountBalance(account);;

        if(amount > accountBalance){
            throw new AtmAmountException("Account don't have enough money");
        }
        else{
            if(cashDispenser.decreaseDispenser(amount)){
                bank.decreaseBalance(account, amount);
            }
            else{
                throw new AtmAmountException("ATM doesn't have enough money");
            }
        }
    }

    public void deposit(String account, double amount){
        cashDispenser.increaseDispenser(amount);
        bank.increaseBalance(account, amount);
    }


}
