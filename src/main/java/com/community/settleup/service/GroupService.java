package com.community.settleup.service;

import com.community.settleup.entity.Group;
import com.community.settleup.entity.User;
import com.community.settleup.repository.GroupRepository;
import com.community.settleup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
}
