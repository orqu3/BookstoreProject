package com.bookstore.admin.service;

import com.bookstore.admin.util.settings.GeneralSettingBag;
import com.bookstore.admin.repository.SettingRepository;
import com.bookstore.common.entity.setting.Setting;
import com.bookstore.common.entity.setting.SettingCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SettingService {

    private final SettingRepository settingRepository;

    public List<Setting> listAllSettings() {
        return (List<Setting>) settingRepository.findAll();
    }

    public GeneralSettingBag getGeneralSettings() {
        List<Setting> settings = new ArrayList<>();

        List<Setting> generalSettings = settingRepository.findByCategory(SettingCategory.GENERAL);
        List<Setting> currencySettings = settingRepository.findByCategory(SettingCategory.CURRENCY);

        settings.addAll(generalSettings);
        settings.addAll(currencySettings);

        return new GeneralSettingBag(settings);
    }

    public void saveAll(Iterable<Setting> settings) {
        settingRepository.saveAll(settings);
    }


    public List<Setting> getMailServerSetting() {
        return settingRepository.findByCategory(SettingCategory.MAIL_SERVER);
    }

    public List<Setting> getMailTemplateSetting() {
        return settingRepository.findByCategory(SettingCategory.MAIL_TEMPLATES);
    }

    public List<Setting> getCurrencySettings(){
        return settingRepository.findByCategory(SettingCategory.CURRENCY);
    }
}
