package com.bookstore.admin.service;

import com.bookstore.admin.repository.SettingRepository;
import com.bookstore.common.entity.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingService {
    @Autowired private SettingRepository repo;

    public List<Setting> listAllSettings(){
        return  (List<Setting>) repo.findAll();
    }
}
