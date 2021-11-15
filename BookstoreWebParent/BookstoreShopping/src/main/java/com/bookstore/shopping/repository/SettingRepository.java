package com.bookstore.shopping.repository;

import com.bookstore.common.entity.Setting;
import com.bookstore.common.entity.SettingCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface SettingRepository extends CrudRepository<Setting, String> {
    List<Setting> findByCategory(SettingCategory category);

    @Query("SELECT s FROM Setting s WHERE s.category = ?1 OR s.category = ?2")
    List<Setting> findByTwoCategories(SettingCategory catOne, SettingCategory catTwo);
}