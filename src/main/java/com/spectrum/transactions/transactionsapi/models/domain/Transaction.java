package com.spectrum.transactions.transactionsapi.models.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Builder
@Document("transactions")
public class Transaction {

    @Id
    private BigInteger id;
    private BigInteger customerId;
    private LocalDateTime date;
    private BigDecimal amount;
    private BigDecimal tax;
    private BigDecimal total;


}
