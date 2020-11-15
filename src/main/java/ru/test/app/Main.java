package ru.test.app;

import ru.test.app.atm.ATM;

public class Main {

    public static void main(String[] args) {
        ATM atm = new ATM();
        if(atm.isAccountAuthenticated()){
            while (!atm.getIsExit()){
                int action = Integer.parseInt(atm.getAtmMenu());
                atm.execute(action);
            }
        }
    }
}
