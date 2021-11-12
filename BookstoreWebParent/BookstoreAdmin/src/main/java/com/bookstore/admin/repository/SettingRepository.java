package com.bookstore.admin.repository;

import com.bookstore.common.entity.Setting;
import org.springframework.data.repository.CrudRepository;

public interface SettingRepository extends CrudRepository<Setting, String> {

}