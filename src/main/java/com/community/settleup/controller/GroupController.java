package com.community.settleup.controller;

import com.community.settleup.entity.Group;
import com.community.settleup.request.GroupRequest;
import com.community.settleup.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public Group createGroup(@RequestBody GroupRequest groupRequest) {
        return groupService.createGroup(groupRequest.getName(), groupRequest.getMembersIds());
    }
}
