package com.agileengine.backendTest.accountingNotebook.accounts.entity;

import com.agileengine.backendTest.accountingNotebook.accounts.service.AccountService;
import com.agileengine.backendTest.accountingNotebook.transactions.entity.Transaction;
import com.agileengine.backendTest.accountingNotebook.transactions.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Account {
    private final String id;
    private BigDecimal balance;

    public boolean hasInsufficientBalanceForTransaction(Transaction transaction) {
        return transaction.getType() == TransactionType.DEBIT
            && balance.subtract(transaction.getAmount()).floatValue() < 0;
    }
}
