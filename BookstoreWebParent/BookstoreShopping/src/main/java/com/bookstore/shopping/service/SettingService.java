package com.bookstore.shopping.service;

import com.bookstore.common.entity.Setting;
import com.bookstore.common.entity.SettingCategory;
import com.bookstore.shopping.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SettingService {
    @Autowired private SettingRepository repo;

    public List<Setting> getGeneralSettings(){
        return repo.findByTwoCategories(SettingCategory.GENERAL, SettingCategory.CURRENCY);

    }

}
