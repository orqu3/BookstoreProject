package com.bookstore.shopping.repository;

import com.bookstore.common.entity.Setting;
import com.bookstore.common.entity.SettingCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@ComponentScan(basePackages={"com.bookstore.shopping.repository"})
@EntityScan(basePackages="com.bookstore.common.entity")
public class SettingRepositoryTests {

    @Autowired SettingRepository repo;

    @Test
    public void testFindByTwoCategories(){
        List<Setting> settings = repo.findByTwoCategories(SettingCategory.GENERAL, SettingCategory.CURRENCY);
        settings.forEach(System.out::println);
    }
}
