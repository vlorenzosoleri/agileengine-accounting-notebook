package com.agileengine.backendTest.accountingNotebook.web;

import com.agileengine.backendTest.accountingNotebook.accounts.service.AccountService;
import com.agileengine.backendTest.accountingNotebook.transactions.entity.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("transactions")
public class TransactionsController {
    private final AccountService accountService;

    public TransactionsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<Collection<Transaction>> getTransactionHistory() {
        return ResponseEntity.ok(accountService.getTransactionHistory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable String id) {
        try {
            return accountService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid ID supplied.");
        }
    }

    @PostMapping
    public ResponseEntity<?> postTransaction(@RequestBody TransactionBody transactionBody) {
        return accountService.storeTransaction(transactionBody)
            .map(Object.class::cast)
            .map(transaction -> ResponseEntity.status(HttpStatus.CREATED).body(transaction))
            .orElseGet(() -> ResponseEntity.badRequest().body("Not allowed to debit more money than you currently have"));
    }
}
