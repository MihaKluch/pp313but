package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserDao {
    User getUserByEmail(String email);
    List<User> listUsers();
    void add(User user);
    User getUserById(Integer id);
    void updateUser(Integer id, User user);
    void deleteUserById(Integer id);
}