package com.spectrum.transactions.transactionsapi.services;

import com.spectrum.transactions.transactionsapi.models.domain.Transaction;
import com.spectrum.transactions.transactionsapi.services.rewards.RewardsProcessor;
import com.spectrum.transactions.transactionsapi.models.domain.RewardPoints;
import com.spectrum.transactions.transactionsapi.models.dtos.TransactionRewardsDto;
import com.spectrum.transactions.transactionsapi.repositories.TransactionsRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class TransactionsService {

    public static final int DEFAULT_REWARD_TIME_PERIOD = 3;
    private Set<RewardsProcessor> rewardProcessors;
    private TransactionsRepository transactionsRepository;

    public TransactionsService(Set<RewardsProcessor> rewardProcessors, TransactionsRepository transactionsRepository) {
        this.rewardProcessors = rewardProcessors;
        this.transactionsRepository = transactionsRepository;
    }

    public TransactionRewardsDto calculateRewards(BigInteger customerId) {
        RewardPoints rewardPoints = RewardPoints.builder().build();
        List<Transaction> transactions = transactionsRepository.findByCustomerIdAndDateAfter(customerId, LocalDateTime.now().minusMonths(DEFAULT_REWARD_TIME_PERIOD));
        for (Transaction transaction : transactions) {
            rewardProcessors.forEach(processor -> processor.processRewards(transaction, rewardPoints));
        }

        return TransactionRewardsDto.builder().customerId(customerId).points(rewardPoints).build();
    }

}
