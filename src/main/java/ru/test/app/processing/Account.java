package ru.test.app.processing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String account;
    private String pin;
    private double balance;
    private Currency currency;
}
