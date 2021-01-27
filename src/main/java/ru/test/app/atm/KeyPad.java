package ru.test.app.atm;

import java.util.Scanner;

public class KeyPad {

    private Scanner input = new Scanner(System.in);

    public String getInput(){
        return input.nextLine();
    }

    public double getAmount(){
        String amount = getInput();
        return amount.isEmpty() ? 0.00 : Double.valueOf(amount);
    }
}
