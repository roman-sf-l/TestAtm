package ru.test.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.test.app.atm.*;
import ru.test.app.config.Config;
import ru.test.app.exception.AtmAmountException;
import ru.test.app.processing.SberBank;

import java.util.HashMap;
import java.util.Map;

@ComponentScan(basePackages = "ru.test.app.config")
@Slf4j
public class Main {

    public static void main(String[] args) {
        Screen screen = new Screen();
        KeyPad keyPad = new KeyPad();

        //initialize ATM with cash dispenser and Bank
        ATM atm = new ATM(new CashDispenser(1000), new SberBank());

        screen.displayMessage("Please enter a card number:");
        String account = keyPad.getInput();

        screen.displayMessage("Please enter a card pin:");
        String pin = keyPad.getInput();

        account = "1234567891234567";
        pin = "1234";

        if(atm.checkAccount(account, pin)){
            screen.displayMessage("Choose an action:");
            screen.displayMessage(atm.getAtmMenu());

            String action = keyPad.getInput();

            Map<String, String> balance = new HashMap<>(2);
            double amount = 0;

            switch (Actions.valueOf(action.toUpperCase())){
                case BALANCE:
                    break;
                case WITHDRAWAL:
                    screen.displayMessage("Enter amount:");
                     amount = keyPad.getAmount();
                    if(amount < 0){
                        screen.displayMessage("Amount cannot be less 0");
                    }else{
                        try {
                            atm.withdrawal(account, amount);
                        } catch (AtmAmountException e) {
                            log.error("Error: " + e.getMessage());
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case DEPOSIT:
                    screen.displayMessage("Enter amount:");
                    amount = keyPad.getAmount();
                    if(amount < 0){
                        screen.displayMessage("Amount cannot be less 0");
                    }else{
                        atm.deposit(account, amount);
                    }
                    break;
            }
            balance = atm.getAccountBalance(account);
            screen.displayMessage("Balance: " + balance.get("amount") + " " + balance.get("currency"));

        }
        else {
            screen.displayMessage("Card not found");
        }

        //getAnnotationConfigContext();
    }

    public static void getAnnotationConfigContext(){
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Config obj =  context.getBean("config", Config.class);
        String msg = obj.getMsg();
        System.out.println(msg);
    }
}
