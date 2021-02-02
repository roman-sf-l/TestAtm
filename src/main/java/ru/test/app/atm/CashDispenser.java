package ru.test.app.atm;

import lombok.Data;

@Data
public class CashDispenser {

    private double cashAmount;

    public CashDispenser(double cashAmount) {
        this.cashAmount = cashAmount;
    }

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
