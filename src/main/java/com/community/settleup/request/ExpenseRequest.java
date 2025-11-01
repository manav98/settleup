package com.community.settleup.request;

import lombok.Data;

import java.util.List;

@Data
public class ExpenseRequest {
    private String description;
    private Double amount;
    private List<Long> participantIds;
    private Long groupId;
    private Long paidByUserId;
}
