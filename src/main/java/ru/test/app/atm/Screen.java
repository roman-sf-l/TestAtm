package ru.test.app.atm;

import lombok.Data;

@Data
public class Screen {

    public void displayMessage(String message){
        System.out.println(message);
    }
}
