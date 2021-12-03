package com.bookstore.shopping.controller;

import com.bookstore.shopping.exception.CustomerNotFoundException;
import com.bookstore.shopping.service.CustomerService;
import com.bookstore.shopping.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final CustomerService customerService;
    private final SettingService settingService;

    @GetMapping("/forgot_password")
    public String showRequestForm() {
        return "customer/forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processRequestForm(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        try {
            String token = customerService.updateResetPasswordToken(email);
        } catch (CustomerNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "customer/forgot_password_form";
    }

    private void sendEmail() {

    }

}
