package ru.test.app.config;

import org.springframework.stereotype.Component;

@Component("config")
public class Config {

    private String msg = "Test message";

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "Test{" +
                "msg='" + msg + '\'' +
                '}';
    }
}