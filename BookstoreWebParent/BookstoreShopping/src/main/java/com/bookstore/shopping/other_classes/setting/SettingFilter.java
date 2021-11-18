package com.bookstore.shopping.other_classes.setting;

import com.bookstore.common.entity.Setting;
import com.bookstore.shopping.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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

        if(url.endsWith(".css") || url.endsWith(".js") || url.endsWith(".png") || url.endsWith(".jpg")){
            chain.doFilter(request, response);
            return;
        }

        List<Setting> generalSettings = service.getGeneralSettings();

        generalSettings.forEach(setting -> {
            System.out.println(setting);
            request.setAttribute(setting.getKey(), setting.getValue());
        });

        chain.doFilter(request, response);
    }
}
