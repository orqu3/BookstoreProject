package com.bookstore.admin.controller;

import com.bookstore.admin.security.BookstoreUserDetails;
import com.bookstore.admin.service.UserService;
import com.bookstore.common.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    @GetMapping("/account")
    public String viewDetails(@AuthenticationPrincipal BookstoreUserDetails loggedUser, Model model) {
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        model.addAttribute("user", user);
        return "account_form";
    }

    @PostMapping("/account/update")
    public String saveUser(User user, RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal BookstoreUserDetails loggedUser) {
        userService.updateAccount(user);
        loggedUser.setFirstName(user.getFirstName());
        loggedUser.setLastName(user.getLastName());
        redirectAttributes.addFlashAttribute("message", "The account details have been updated successfully.");
        return "redirect:/account";
    }
}
