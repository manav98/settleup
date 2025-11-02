package com.community.settleup.service;

import com.community.settleup.entity.Group;
import com.community.settleup.entity.User;
import com.community.settleup.repository.GroupRepository;
import com.community.settleup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public Group createGroup(String name, List<Long> membersIds) {
        LocalDate createdDate = LocalDate.now();
        List<User> groupMembers = userRepository.findAllById(membersIds);
        if (groupMembers.size() != membersIds.size()) {
            throw new IllegalArgumentException("Some user IDs are invalid or missing!");
        }
        Group group = Group.builder()
                .name(name)
                .createdDate(createdDate)
                .members(groupMembers).build();
        return groupRepository.save(group);
    }

    public Map<String, Double> getGroupBalances(Long groupId) {
        Group currentGroup = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found. Invalid Group ID"));
        List<User> members = currentGroup.getMembers();
        return members.stream().collect(Collectors.toMap(User::getUserName, User::getBalance));
    }

}
