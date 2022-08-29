package com.spectrum.transactions.transactionsapi.models.dtos;

import com.spectrum.transactions.transactionsapi.models.domain.RewardPoints;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
public class TransactionRewardsDto {

    private RewardPoints points;
    private BigInteger customerId;

}
