package com.bankingsystem.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AccountType {
    SAVINGS,
    CURRENT;

    @JsonCreator
    public static AccountType fromValue(String value) {
        if (value == null) {
            return null;
        }
        return AccountType.valueOf(value.toUpperCase());
    }
}
