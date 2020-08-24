package com.agileengine.backendTest.accountingNotebook.web;

import com.agileengine.backendTest.accountingNotebook.accounts.entity.Account;
import com.agileengine.backendTest.accountingNotebook.accounts.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("default")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<Account> getCurrentAccountBalance() {
        return ResponseEntity.ok(accountService.getCurrentAccount());
    }
}
