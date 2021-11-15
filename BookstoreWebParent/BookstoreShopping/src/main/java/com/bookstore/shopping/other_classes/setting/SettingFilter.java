package com.bookstore.shopping.other_classes.setting;

import com.bookstore.shopping.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@ComponentScan(basePackages={"com.bookstore.shopping.repository"})
@EntityScan(basePackages="com.bookstore.common.entity")
public class SettingFilter implements Filter {

    @Autowired
    private SettingService service;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String url = servletRequest.getRequestURL().toString();

        System.out.println(url);

        chain.doFilter(request, response);
    }
}
