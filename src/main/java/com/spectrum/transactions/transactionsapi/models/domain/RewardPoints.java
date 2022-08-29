package com.spectrum.transactions.transactionsapi.models.domain;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class RewardPoints {

    @Builder.Default
    private Integer total = 0;

    public void addPoints(Integer points){
        total+=points;
    }
    public void removePoints(Integer points){
        total=Math.max(0, total - points);
    }
}
