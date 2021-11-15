package com.bookstore.admin.service;

import com.bookstore.admin.other_classes.setting.GeneralSettingBag;
import com.bookstore.admin.repository.SettingRepository;
import com.bookstore.common.entity.Setting;
import com.bookstore.common.entity.SettingCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SettingService {
    @Autowired private SettingRepository repo;

    public List<Setting> listAllSettings(){
        return  (List<Setting>) repo.findAll();
    }

    public GeneralSettingBag getGeneralSettings(){
        List<Setting> settings = new ArrayList<>();

        List<Setting> generalSettings = repo.findByCategory(SettingCategory.GENERAL);
        List<Setting> currencySettings = repo.findByCategory(SettingCategory.CURRENCY);

        settings.addAll(generalSettings);
        settings.addAll(currencySettings);

        return new GeneralSettingBag(generalSettings);

    }

    public void saveAll(Iterable<Setting> settings){
        repo.saveAll(settings);
    }
}
