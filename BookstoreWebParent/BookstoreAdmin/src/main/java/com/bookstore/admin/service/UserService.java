package com.bookstore.admin.service;

import com.bookstore.admin.repository.UserRepository;
import com.bookstore.common.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }
}
