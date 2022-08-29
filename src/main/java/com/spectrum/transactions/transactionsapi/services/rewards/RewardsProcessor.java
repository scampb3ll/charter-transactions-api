package com.spectrum.transactions.transactionsapi.services.rewards;

import com.spectrum.transactions.transactionsapi.models.domain.RewardPoints;
import com.spectrum.transactions.transactionsapi.models.domain.Transaction;

public interface RewardsProcessor {

    void processRewards(Transaction transaction,RewardPoints rewards);
}
