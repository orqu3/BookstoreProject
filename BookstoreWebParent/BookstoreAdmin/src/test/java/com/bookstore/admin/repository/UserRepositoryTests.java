package com.bookstore.admin.repository;

import com.bookstore.common.entity.Role;
import com.bookstore.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateAdmin() {
        Role roleAdmin = entityManager.find(Role.class, 1);
        User admin = new User("admin@gmail", "Gven", "Doe", "$2a$10$Op29XN5Se3.XsCcWzBYpuuJhZqDYG0/MZof5PIN5RpeC9cR13hXw2", true);
        admin.addRole(roleAdmin);

        User savedAdmin = userRepository.save(admin);
        assertThat(savedAdmin.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateSalesperson() {
        Role roleSalesperson = entityManager.find(Role.class, 2);
        User salesperson = new User("salesperson@gmail", "Tim", "Waterstone", "$2a$10$Op29XN5Se3.XsCcWzBYpuuJhZqDYG0/MZof5PIN5RpeC9cR13hXw2", true);
        salesperson.addRole(roleSalesperson);

        User savedSalesperson = userRepository.save(salesperson);
        assertThat(savedSalesperson.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers() {
        Iterable<User> listUsers = userRepository.findAll();
        listUsers.forEach(System.out::println);
    }

    @Test
    public void getUserById() {
        User admin = userRepository.findById(1).get();
        System.out.println(admin);
        assertThat(admin).isNotNull();
    }

    @Test
    public void testUpdateUserDetails() {
        User admin = userRepository.findById(1).get();
        admin.setEnabled(true);
        admin.setEmail("admin@yandex.com");
        userRepository.save(admin);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "admin@yandex.com";
        User user = userRepository.getUserByEmail(email);
        assertThat(user).isNotNull();
    }

    @Test
    public void testCountById() {
        Integer id = 1;
        Long countById = userRepository.countById(id);
        assertThat(countById).isNotNull().isGreaterThan(0);
    }

    @Test
    public void testDisableUser() {
        Integer id = 2;
        userRepository.updateEnabledStatus(id, false);
    }

    @Test
    public void testEnableUser() {
        Integer id = 2;
        userRepository.updateEnabledStatus(id, true);
    }

    @Test
    public void testListFirstPage() {
        int pageNumber = 0;
        int pageSize = 4;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> page = userRepository.findAll(pageable);
        List<User> listUsers = page.getContent();
        listUsers.forEach(System.out::println);
        assertThat(listUsers.size()).isEqualTo(pageSize);
    }

    @Test
    public void testSearchUsersByKeyword() {
        String keyword = "john";
        int pageNumber = 0;
        int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> page = userRepository.findAll(keyword, pageable);
        List<User> listUsers = page.getContent();
        listUsers.forEach(System.out::println);
        assertThat(listUsers.size()).isGreaterThan(0);
    }
}

