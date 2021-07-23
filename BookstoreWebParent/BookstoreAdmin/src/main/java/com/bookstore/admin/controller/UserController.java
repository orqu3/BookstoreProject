package com.bookstore.admin.controller;

import com.bookstore.admin.service.UserService;
import com.bookstore.common.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String listAll(Model model) {
        List<User> users = userService.listAll();
        model.addAttribute("users", users);
        return "users";
    }
}
