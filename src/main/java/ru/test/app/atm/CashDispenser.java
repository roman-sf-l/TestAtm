package ru.test.app.atm;

public class CashDispenser {

    private static float cashAmount = 100000;

    public void increaseDispenser(float amount){
        cashAmount += amount;
    }

    public boolean decreaseDispenser(float amount){
        if(cashAmount < amount){
            return false;
        }
        cashAmount -= amount;
        return true;
    }
}
