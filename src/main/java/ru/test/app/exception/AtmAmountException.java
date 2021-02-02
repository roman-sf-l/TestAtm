package ru.test.app.exception;

public class AtmAmountException extends  Exception{
    public AtmAmountException() {
    }

    public AtmAmountException(String s) {
        super(s);
    }

    public AtmAmountException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AtmAmountException(Throwable throwable) {
        super(throwable);
    }

    public AtmAmountException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
