package ru.test.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.test.app.atm.ATM;
import ru.test.app.config.Config;

@ComponentScan(basePackages = "ru.test.app.config")
public class Main {

    public static void main(String[] args) {

        ATM atm = new ATM();
        if(atm.isAccountAuthenticated()){
            while (!atm.getIsExit()){
                int action = Integer.parseInt(atm.getAtmMenu());
                atm.execute(action);
            }
        }

        getAnnotationConfigContext();
    }

    public static void getAnnotationConfigContext(){
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Config obj =  context.getBean("config", Config.class);
        String msg = obj.getMsg();
        System.out.println(msg);
    }
}
