package com.spectrum.transactions.transactionsapi.repositories;

import com.spectrum.transactions.transactionsapi.models.domain.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionsRepository extends MongoRepository<Transaction, BigInteger> {
    List<Transaction> findByCustomerIdAndDateAfter(BigInteger customerId, LocalDateTime date);
}
