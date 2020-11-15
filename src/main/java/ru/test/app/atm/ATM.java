package ru.test.app.atm;

import ru.test.app.processing.Bank;

import java.util.Arrays;

public class ATM {

    private boolean isAccountAuthenticated = false;
    private boolean isExit = false;
    private String account;
    private Screen screen = new Screen();
    private KeyPad keyPad = new KeyPad();
    private Bank bank = new Bank();
    private CashDispenser cashDispenser;

    private enum Actions{
        BALANCE_INQUIRY,
        WITHDRAWAL,
        DEPOSIT,
        EXIT
    }

    public boolean isAccountAuthenticated(){
        screen.displayMessage("Please enter a card number: ");
        account = keyPad.getInput();

        if(account.matches("\\d{16,}")){
            screen.displayMessage("Please enter a pin code: ");
            String pin = keyPad.getInput();

            if(pin.matches("\\d{4}")){
                if(bank.isAccountCorrect(account, pin)){
                    isAccountAuthenticated = true;
                }
                else{
                    screen.displayMessage("Cannot find a card.");
                }
            }
            else {
                screen.displayMessage("You entered an invalid pin code.");
            }
        }
        else{
            screen.displayMessage("You entered an invalid account number.");
        }

        return isAccountAuthenticated;
    }

    public String getAtmMenu(){
        screen.displayMessage("Please choose an action:");
        Arrays.stream(Actions.values()).forEach(action -> screen.displayMessage(action.ordinal() + 1 + ". " + action.toString().replace("_", " ")));

        return keyPad.getInput();
    }

    public void execute(int actionIndex){
        if(actionIndex > 0 && Arrays.stream(Actions.values()).count() >= actionIndex){
            Actions action = Actions.values()[actionIndex - 1];

            switch (action){
                case BALANCE_INQUIRY:
                    screen.displayMessage("Balance: " + bank.getAccountBalance(account) + " " + bank.getAccountCurrency(account));
                    break;
                case EXIT:
                    screen.displayMessage("See you later!");
                    isExit = true;
                    break;
            }
        }
    }

    public boolean getIsExit(){
        return isExit;
    }

}
