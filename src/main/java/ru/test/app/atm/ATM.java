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
    private CashDispenser cashDispenser = new CashDispenser();

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

        String menuIndex = keyPad.getInput();
        return menuIndex.matches("\\d+") ? menuIndex : "-1";
    }

    public void execute(int actionIndex){
        if(actionIndex > 0 && Arrays.stream(Actions.values()).count() >= actionIndex){
            Actions action = Actions.values()[actionIndex - 1];

            switch (action){
                case BALANCE_INQUIRY:
                    screen.displayMessage("Balance: " + bank.getAccountBalance(account) + " " + bank.getAccountCurrency(account));
                    break;
                case WITHDRAWAL:
                    double amountWithdrawal = getEnteredAmount();
                    if(!isAmountIllegal(amountWithdrawal)){
                        withdrawal(amountWithdrawal);
                    }
                    break;
                case DEPOSIT:
                    double amountDeposit = getEnteredAmount();
                    if(!isAmountIllegal(amountDeposit)){
                        deposit(amountDeposit);
                    }
                    break;
                case EXIT:
                    screen.displayMessage("See you later!");
                    isExit = true;
                    break;
            }
        }
    }

    private void withdrawal(double amount){
        double accountBalance = Double.parseDouble(bank.getAccountBalance(account));
        if(amount > accountBalance){
            screen.displayMessage("You don't have enough money.");
        }
        else{
            if(cashDispenser.decreaseDispenser(amount)){
                bank.decreaseBalance(account, amount);
            }
            else{
                screen.displayMessage("ATM doesn't have enough money.");
            }
        }
    }

    private void deposit(double amount){
        cashDispenser.increaseDispenser(amount);
        bank.increaseBalance(account, amount);
    }

    private double getEnteredAmount(){
        screen.displayMessage("Enter amount:");
        String amount = keyPad.getInput();
        return amount.matches("[\\d.]+") ? Double.parseDouble(amount) : 0;
    }

    private boolean isAmountIllegal(double amount){
        if(amount <= 0){
            screen.displayMessage("Amount cannot be 0 or less than 0.");
            return true;
        }
        return false;
    }

    public boolean getIsExit(){
        return isExit;
    }

}
