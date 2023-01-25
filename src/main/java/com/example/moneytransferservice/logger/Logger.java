package com.example.moneytransferservice.logger;

public interface Logger {
    void log(String msg);

    static Logger getInstance() {
        return null;
    }
}
