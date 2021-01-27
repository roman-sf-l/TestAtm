package ru.test.app.processing;

public interface Bank {

    boolean isAccountCorrect(String account, String pin);
    Double getAccountBalance(String account);
    Currency getAccountCurrency(String account);
    void decreaseBalance(String clientAccount, double clientAmount);
    void increaseBalance(String clientAccount, double clientAmount);
}
