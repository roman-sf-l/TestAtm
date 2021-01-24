package ru.test.app.processing;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Bank {
    // {'accountNumber': {'pin': 1234, 'currency': 'EUR', 'balance': 100.00}}
    private static Map<String, Map<String, String>> accounts = new HashMap<>();

    public Bank(){
        Map<String, String> accountDetails = new HashMap<>();
        accountDetails.put("pin", "1234");
        accountDetails.put("currency", "EUR");
        accountDetails.put("balance", "100.00");

        accounts.put("`1234567891234567`", accountDetails);

        Map<String, String> accountDetails2 = new HashMap<>();
        accountDetails2.put("pin", "4321");
        accountDetails2.put("currency", "RUR");
        accountDetails2.put("balance", "10000.00");

        accounts.put("2121222122121212", accountDetails2);
    }

    public boolean isAccountCorrect(String account, String pin){
        Optional<Map<String, String>> accountNumber = Optional.ofNullable(accounts.get(account));
        return accountNumber.isPresent() && accountNumber.get().get("pin").equals(pin);
    }

    public String getAccountBalance(String account){
        return accounts.entrySet().stream()
                .filter(item -> item.getKey().equals(account))
                .map(key -> key.getValue().get("balance"))
                .findFirst()
                .orElse("0.00");
    }

    public String getAccountCurrency(String account){
        return accounts.entrySet().stream()
                .filter(item -> item.getKey().equals(account))
                .map(key -> key.getValue().get("currency"))
                .findFirst()
                .get();
    }

    public void increaseBalance(String account, double amount){
        accounts.get(account).computeIfPresent("balance", (key, value) ->
                { Double balance = Double.parseDouble(value) + amount; return balance.toString(); }
            );
    }

    public void decreaseBalance(String account, double amount){
        accounts.get(account).computeIfPresent("balance", (key, value) ->
                { Double balance = Double.parseDouble(value) - amount; return balance.toString(); }
        );
    }
}
