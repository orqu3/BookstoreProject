package com.bookstore.shopping.service;


import com.bookstore.common.entity.Category;
import com.bookstore.shopping.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public List<Category> listNoChildrenCategories() {
        List<Category> listNoChildrenCategories = new ArrayList<>();


        List<Category> listEnabledCategories = repo.findAllEnable();
        listEnabledCategories.forEach(category -> {
            Set<Category> children = category.getChildren();
            if (children == null || children.size() == 0) {
                listNoChildrenCategories.add(category);
            }

        });

        return listNoChildrenCategories;
    }

    public Category getCategory(String alias) {
        return repo.findByAliasEnabled(alias);
    }

    public List<Category> getCategoryParents(Category child) {
        List<Category> listParent = new ArrayList<>();


        Category parent = child.getParent();

        while (parent != null) {
            listParent.add(0, parent);
            parent = parent.getParent();

        }

        listParent.add(child);

        return listParent;


    }
}