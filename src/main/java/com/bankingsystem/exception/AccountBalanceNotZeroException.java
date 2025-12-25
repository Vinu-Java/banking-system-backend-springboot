package com.bankingsystem.exception;

public class AccountBalanceNotZeroException extends RuntimeException {
    public AccountBalanceNotZeroException(String message) {
        super(message);
    }
}
