package com.agileengine.backendTest.accountingNotebook.web;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionBody {
    private String type;
    private BigDecimal amount;
}
