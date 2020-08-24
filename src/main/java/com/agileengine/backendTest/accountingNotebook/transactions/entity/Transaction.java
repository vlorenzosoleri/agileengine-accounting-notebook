package com.agileengine.backendTest.accountingNotebook.transactions.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class Transaction {
    private final UUID id;
    private final TransactionType type;
    private final BigDecimal amount;
    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-ddTHH:mm:ss.SSSZ"
    )
    private final ZonedDateTime effectiveDate;

    @JsonIgnore
    public BigDecimal getAmountForBalance() {
        if (type == TransactionType.DEBIT) {
            return amount.abs().negate();
        }
        if (type == TransactionType.CREDIT) {
            return amount.abs();
        }
        return amount;
    }
}
