package com.spectrum.transactions.transactionsapi.services.rewards;

import com.spectrum.transactions.transactionsapi.models.domain.Transaction;
import com.spectrum.transactions.transactionsapi.models.domain.RewardPoints;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TwoForHundredProcessor implements RewardsProcessor{


    @Override
    public void processRewards(@NonNull final Transaction transaction, RewardPoints rewards) {
        if(transaction.getTotal().intValue() < 100){  return; } //no reward
        rewards.addPoints(transaction.getTotal()
                .subtract(BigDecimal.valueOf(100))
                .multiply(BigDecimal.valueOf(2))
                .intValue());
    }
}
