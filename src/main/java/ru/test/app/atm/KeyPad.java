package ru.test.app.atm;

import java.util.Scanner;

public class KeyPad {

    private Scanner input;

    public KeyPad(){
        input = new Scanner(System.in);
    }

    public String getInput(){
        return input.nextLine();
    }
}
