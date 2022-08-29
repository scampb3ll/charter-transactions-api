package com.spectrum.transactions.transactionsapi.controllers;

import com.spectrum.transactions.transactionsapi.models.domain.Transaction;
import com.spectrum.transactions.transactionsapi.repositories.TransactionsRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TransactionsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @BeforeEach
    public void setupData(){
        transactionsRepository.deleteAll();
        transactionsRepository.save(Transaction.builder().id(BigInteger.valueOf(1L)).customerId(BigInteger.valueOf(1L)).total(BigDecimal.valueOf(120.00)).date(LocalDateTime.now().minusMonths(1)).build());
        transactionsRepository.save(Transaction.builder().id(BigInteger.valueOf(2L)).customerId(BigInteger.valueOf(1L)).total(BigDecimal.valueOf(150.00)).date(LocalDateTime.now().minusMonths(6)).build()); //should be outside the default time frame
        transactionsRepository.save(Transaction.builder().id(BigInteger.valueOf(3L)).customerId(BigInteger.valueOf(1L)).total(BigDecimal.valueOf(80.00)).date(LocalDateTime.now().minusMonths(2)).build());
        transactionsRepository.save(Transaction.builder().id(BigInteger.valueOf(4L)).customerId(BigInteger.valueOf(1L)).total(BigDecimal.valueOf(40.00)).date(LocalDateTime.now().minusMonths(1)).build());
    }

    @Test
    public void testHappyPath() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/transactions/rewards/customer/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.points.total").value("120"));
    }
}