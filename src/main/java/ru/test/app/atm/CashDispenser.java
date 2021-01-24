package ru.test.app.atm;

public class CashDispenser {

    private static double cashAmount = 100000;

    public void increaseDispenser(double amount){
        cashAmount += amount;
    }

    public boolean decreaseDispenser(double amount){
        if(cashAmount < amount){
            return false;
        }
        cashAmount -= amount;
        return true;
    }
}
