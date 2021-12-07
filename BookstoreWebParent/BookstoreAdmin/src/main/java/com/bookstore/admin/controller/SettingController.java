package com.bookstore.admin.controller;

import com.bookstore.admin.util.settings.GeneralSettingBag;
import com.bookstore.admin.repository.CurrencyRepository;
import com.bookstore.admin.service.SettingService;
import com.bookstore.common.entity.Currency;
import com.bookstore.common.entity.Setting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService;
    private final CurrencyRepository currencyRepository;

    @GetMapping("/settings")
    public String listAll(Model model) {
        List<Setting> listSettings = settingService.listAllSettings();
        List<Currency> listCurrencies = currencyRepository.findAllByOrderByNameAsc();

        for (Setting setting : listSettings) {
            model.addAttribute(setting.getKey(), setting.getValue());
        }
        model.addAttribute("listCurrencies", listCurrencies);
        return "settings/settings";
    }

    @PostMapping("/settings/save_general")
    public String saveGeneralSettings(HttpServletRequest request, RedirectAttributes ra) {
        GeneralSettingBag settingBag = settingService.getGeneralSettings();

        saveCurrencySymbol(request, settingBag);

        updateSettingValuesFromForm(request, settingBag.list());

        ra.addFlashAttribute("message", "General settings have been saved.");
        return "redirect:/settings";

    }

    private void saveCurrencySymbol(HttpServletRequest request, GeneralSettingBag settingBag) {
        Integer currencyId = Integer.parseInt(request.getParameter("CURRENCY_ID"));
        Optional<Currency> findByIdResult = currencyRepository.findById(currencyId);

        if (findByIdResult.isPresent()) {
            Currency currency = findByIdResult.get();
            settingBag.updateCurrencySymbol(currency.getSymbol());
        }
    }

    private void updateSettingValuesFromForm(HttpServletRequest request, List<Setting> listSettings) {
        for (Setting setting : listSettings) {
            String value = request.getParameter(setting.getKey());
            if (value != null) {
                setting.setValue(value);
            }
        }
        settingService.saveAll(listSettings);
    }

    @PostMapping("/settings/save_mail_server")
    public String saveMailServerSettings(HttpServletRequest request, RedirectAttributes ra) {
        List<Setting> mailServerSettings = settingService.getMailServerSetting();
        updateSettingValuesFromForm(request, mailServerSettings);

        ra.addFlashAttribute("message", "Mail server settings have been saved");

        return "redirect:/settings";
    }

    @PostMapping("/settings/save_mail_templates")
    public String saveMailTemplateSettings(HttpServletRequest request, RedirectAttributes ra) {
        List<Setting> mailTemplateSetting = settingService.getMailTemplateSetting();
        updateSettingValuesFromForm(request, mailTemplateSetting);

        ra.addFlashAttribute("message", "Mail template settings have been saved");

        return "redirect:/settings";
    }
}
