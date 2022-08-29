package com.spectrum.transactions.transactionsapi.controllers;

import com.spectrum.transactions.transactionsapi.models.dtos.TransactionRewardsDto;
import com.spectrum.transactions.transactionsapi.services.TransactionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    private TransactionsService transactionsService;

    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping("/rewards/customer/{id}")
    public ResponseEntity<TransactionRewardsDto> calculateRewards(@PathVariable BigInteger id){
        TransactionRewardsDto dto = transactionsService.calculateRewards(id);
        return ResponseEntity.ok(dto);
    }
}
