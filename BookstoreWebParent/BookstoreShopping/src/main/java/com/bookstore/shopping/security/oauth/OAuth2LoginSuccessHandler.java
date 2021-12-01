package com.bookstore.shopping.security.oauth;

import com.bookstore.common.entity.AuthenticationType;
import com.bookstore.common.entity.Customer;
import com.bookstore.shopping.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final CustomerService customerService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        CustomerOAuth2User oAuth2User = (CustomerOAuth2User) authentication.getPrincipal();

        String name = oAuth2User.getName();
        String email = oAuth2User.getEmail();
        String countryCode = request.getLocale().getCountry();

        Customer customer = customerService.getCustomerByEmail(email);
        if(customer == null) {
            customerService.addNewCustomerUponOAuthLogin(name, email, countryCode);
        } else {
            customerService.updateAuthenticationType(customer, AuthenticationType.GOOGLE);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
