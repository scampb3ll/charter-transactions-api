package com.spectrum.transactions.transactionsapi.services.rewards;

import com.spectrum.transactions.transactionsapi.models.domain.RewardPoints;
import com.spectrum.transactions.transactionsapi.models.domain.Transaction;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OneForFiftyProcessor implements RewardsProcessor{
    // Notes A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
    // This seems to imply that its 1 point for every point above $50 UP TO $100..or we would have a value of 110 reward points. I am operating on that assumption..:) In the real world this is a requirements grooming question ;)

    @Override
    public void processRewards(@NonNull final Transaction transaction, RewardPoints rewards) {
        if(transaction.getTotal().intValue() < 50){  return; } //no reward
         rewards.addPoints(Math.min(50, transaction.getTotal()
                 .subtract(BigDecimal.valueOf(50))
                 .intValue()) );
    }
}
