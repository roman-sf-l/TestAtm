package ru.test.app.processing;

import java.util.ArrayList;

public class Bank {
    private ArrayList<Account> accounts = new ArrayList<>();

    public Bank(){
        Account account1 = new Account("1234567891234567", "1234", 100.00, Currency.RUB);
        Account account2 = new Account("2121222122121212", "4321", 200.00, Currency.USD);

        accounts.add(account1);
        accounts.add(account2);
    }

    public boolean isAccountCorrect(String account, String pin){
        return accounts.stream().anyMatch(a -> a.getAccount().equals(account) && a.getPin().equals(pin));
    }

    public Double getAccountBalance(String account){
        return accounts.stream().filter(a -> a.getAccount().equals(account)).findFirst().get().getBalance();
    }

    public Currency getAccountCurrency(String account){
        return accounts.stream().filter(a -> a.getAccount().equals(account)).findFirst().get().getCurrency();
    }

    public void decreaseBalance(String clientAccount, double clientAmount){
        for(Account account: accounts){
            if(account.getAccount().equals(clientAccount)){
                account.setBalance(account.getBalance() - clientAmount);
            }
        }
    }

    public void increaseBalance(String clientAccount, double clientAmount){
        for(Account account: accounts){
            if(account.getAccount().equals(clientAccount)){
                account.setBalance(account.getBalance() + clientAmount);
            }
        }
    }

}
