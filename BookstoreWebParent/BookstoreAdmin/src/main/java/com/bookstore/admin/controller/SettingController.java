package com.bookstore.admin.controller;

import com.bookstore.admin.other_classes.setting.GeneralSettingBag;
import com.bookstore.admin.repository.CurrencyRepository;
import com.bookstore.admin.service.SettingService;
import com.bookstore.common.entity.Currency;
import com.bookstore.common.entity.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.util.StringUtils;
import java.io.File;
import java.lang.Object;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("/settings/save_general")
    public String saveGeneralSettings(HttpServletRequest request, RedirectAttributes ra){
        GeneralSettingBag settingBag = service.getGeneralSettings();

        saveCurrencySymbol(request, settingBag);

        updateSettingValuesFromForm(request, settingBag.list());

        ra.addFlashAttribute("message", "General settings have been saved.");
        return "redirect:/settings";

    }

    private void saveCurrencySymbol(HttpServletRequest request, GeneralSettingBag settingBag){
        Integer currencyId = Integer.parseInt(request.getParameter("CURRENCY_ID"));
        Optional<Currency> findByIdResult = currencyRepo.findById(currencyId);

        if(findByIdResult.isPresent()){
            Currency currency = findByIdResult.get();
            settingBag.updateCurrencySymbol(currency.getSymbol());
        }
    }

    private void updateSettingValuesFromForm(HttpServletRequest request, List<Setting> listSettings){
        for(Setting setting : listSettings){
            String value = request.getParameter(setting.getKey());
            if(value != null){
                setting.setValue(value);
            }
        }
        service.saveAll(listSettings);
    }
}
