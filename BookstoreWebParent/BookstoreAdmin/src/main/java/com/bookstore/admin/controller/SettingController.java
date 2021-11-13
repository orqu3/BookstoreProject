package com.bookstore.admin.controller;

import com.bookstore.admin.repository.CurrencyRepository;
import com.bookstore.admin.service.SettingService;
import com.bookstore.common.entity.Currency;
import com.bookstore.common.entity.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SettingController {

    @Autowired private SettingService service;

    @Autowired private CurrencyRepository currencyRepo;

    @GetMapping("/settings")
    public String listAll(Model model){
        List<Setting> listSetting = service.listAllSettings();
        List<Currency> listCurrencies = currencyRepo.findAllByOrderByNameAsc();

        for(Setting setting: listSetting){
            model.addAttribute(setting.getKey(), setting.getValue());
        }
        model.addAttribute("listCurrencies", listCurrencies);
        return"settings/settings";
    }
}
