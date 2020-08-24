package com.agileengine.backendTest.accountingNotebook.accounts.service;

import com.agileengine.backendTest.accountingNotebook.accounts.entity.Account;
import com.agileengine.backendTest.accountingNotebook.transactions.entity.Transaction;
import com.agileengine.backendTest.accountingNotebook.transactions.entity.TransactionType;
import com.agileengine.backendTest.accountingNotebook.transactions.persistence.TransactionRepository;
import com.agileengine.backendTest.accountingNotebook.web.TransactionBody;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {
    private final TransactionRepository transactionRepository;
    private Account currentAccount;

    public AccountService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
        this.currentAccount = new Account("DEFAULT_ACCOUNT", BigDecimal.valueOf(0));
    }

    public Optional<Transaction> getTransactionById(String id) {
        return transactionRepository.findTransactionById(UUID.fromString(id));
    }

    public Collection<Transaction> getTransactionHistory() {
        return transactionRepository.findAllTransactions();
    }

    public Optional<Transaction> storeTransaction(TransactionBody transactionBody) {
        var transaction = makeTransaction(transactionBody);
        if (currentAccount.hasInsufficientBalanceForTransaction(transaction)) {
            return Optional.empty();
        }
        currentAccount.setBalance(currentAccount.getBalance().add(transaction.getAmountForBalance()));
        return Optional.of(transactionRepository.saveTransaction(transaction));
    }

    private Transaction makeTransaction(TransactionBody transactionBody) {
        return new Transaction(
            UUID.randomUUID(),
            TransactionType.from(transactionBody.getType()),
            transactionBody.getAmount(),
            LocalDateTime.now().atZone(ZoneId.systemDefault())
        );
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }
}
