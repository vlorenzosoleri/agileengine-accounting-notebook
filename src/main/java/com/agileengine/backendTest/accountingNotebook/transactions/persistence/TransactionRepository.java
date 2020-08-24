package com.agileengine.backendTest.accountingNotebook.transactions.persistence;

import com.agileengine.backendTest.accountingNotebook.transactions.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {
    private static final Map<UUID, Transaction> TRANSACTION_DB = new ConcurrentHashMap<>();

    public Transaction saveTransaction(Transaction transaction) {
        TRANSACTION_DB.put(transaction.getId(), transaction);
        return transaction;
    }

    public Optional<Transaction> findTransactionById(UUID uuid) {
        return Optional.ofNullable(TRANSACTION_DB.get(uuid));
    }

    public Collection<Transaction> findAllTransactions() {
        return TRANSACTION_DB.values().stream()
            .sorted(Comparator.comparing(Transaction::getEffectiveDate).reversed())
            .collect(Collectors.toList());
    }
}
