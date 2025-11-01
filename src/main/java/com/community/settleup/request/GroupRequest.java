package com.community.settleup.request;

import lombok.Data;

import java.util.List;

@Data
public class GroupRequest {
    private String name;
    private List<Long> membersIds;
}
