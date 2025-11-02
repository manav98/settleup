package com.community.settleup.controller;

import com.community.settleup.entity.Group;
import com.community.settleup.request.GroupRequest;
import com.community.settleup.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<Group> createGroup(@RequestBody GroupRequest groupRequest) {
        return new ResponseEntity<>(
                groupService.createGroup(groupRequest.getName(), groupRequest.getMembersIds()),
                HttpStatus.CREATED);
    }

    @GetMapping("/{groupId}/balances")
    public ResponseEntity<Map<String, Double>> getGroupBalances(@PathVariable Long groupId) {
        return new ResponseEntity<>(groupService.getGroupBalances(groupId), HttpStatus.OK);
    }
}
