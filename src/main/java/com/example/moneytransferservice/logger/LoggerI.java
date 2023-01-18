package com.example.moneytransferservice.logger;

public interface LoggerI {
    void log(String msg);

    static LoggerI getInstance() {
        return null;
    }
}
