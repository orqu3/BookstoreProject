package com.bookstore.shopping.repository;

import com.bookstore.common.entity.setting.Setting;
import com.bookstore.common.entity.setting.SettingCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class SettingRepositoryTests {

    @Autowired
    SettingRepository settingRepository;

    @Test
    public void testFindByTwoCategories() {
        List<Setting> settings = settingRepository.findByTwoCategories(SettingCategory.GENERAL, SettingCategory.CURRENCY);
        settings.forEach(System.out::println);
    }
}
