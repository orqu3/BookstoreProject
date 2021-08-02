package com.bookstore.admin.repository;

import com.bookstore.common.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateFirstRootCategory() {
        Category category = new Category("Art, Music & Photography");
        Category savedCategory = categoryRepository.save(category);
        assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateSecondRootCategory() {
        Category category = new Category("Biographies & Memoirs");
        Category savedCategory = categoryRepository.save(category);
        assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateFirstSubCategory() {
        Category parent = new Category(1);
        Category subCategory = new Category("Art History", parent);
        Category savedCategory = categoryRepository.save(subCategory);
        assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateSecondSubCategory() {
        Category parent = new Category(1);
        Category subCategory = new Category("Calligraphy", parent);
        Category savedCategory = categoryRepository.save(subCategory);
        assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateThirdSubCategory() {
        Category parent = new Category(2);
        Category subCategory = new Category("Ethnic & Cultural", parent);
        Category savedCategory = categoryRepository.save(subCategory);
        assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void testGetCategory() {
        Category category = categoryRepository.findById(1).get();
        System.out.println(category.getName());
        Set<Category> children = category.getChildren();
        for (Category subCategory : children) {
            System.out.println(subCategory.getName());
        }
        assertThat(children.size()).isGreaterThan(0);
    }

    @Test
    public void testPrintHierarchicalCategories() {
        Iterable<Category> categories = categoryRepository.findAll();
        for(Category category : categories) {
            if(category.getParent() == null) {
                System.out.println(category.getName());
                Set<Category> children = category.getChildren();
                for(Category subCategory : children) {
                    System.out.println("-- " + subCategory.getName());
                }
            }
        }
    }

    @Test
    public void testListRootCategories() {
        List<Category> rootCategories = categoryRepository.findRootCategories();
        rootCategories.forEach(category -> System.out.println(category.getName()));
    }
}
