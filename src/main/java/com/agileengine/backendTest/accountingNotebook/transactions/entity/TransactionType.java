package com.agileengine.backendTest.accountingNotebook.transactions.entity;

public enum TransactionType {
    CREDIT("CREDIT"),
    DEBIT("DEBIT");

    TransactionType(String type) {
    }

    public static TransactionType from(String type) {
        if ("CREDIT".equals(type.toUpperCase())) {
            return CREDIT;
        }
        if ("DEBIT".equals(type.toUpperCase())) {
            return DEBIT;
        }
        throw new IllegalArgumentException("Invalid transaction type. Must be CREDIT or DEBIT");
    }
}
