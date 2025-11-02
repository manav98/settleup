package com.community.settleup.service;

import com.community.settleup.entity.User;
import com.community.settleup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(String userName, String email) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("User with this email already exists");
        }
        User newUser = User.builder()
                .userName(userName)
                .email(email)
                .balance(0.0).build();
        return userRepository.save(newUser);
    }
}
