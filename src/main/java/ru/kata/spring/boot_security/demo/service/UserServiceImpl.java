package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.*;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleService roleService) {
        this.userDao = userDao;
        this.roleService = roleService;
    }

    @Override
    public void add(User user, String[] roles) {
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        user.setRoles(Arrays.stream(roles).map(roleService::getRoleByName)
                .collect(Collectors.toList()));
        System.out.println(user);
        userDao.add(user);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public void deleteUserById(Integer id) {
        userDao.deleteUserById(id);
    }

    @Override
    public void updateUser(Integer id, User user, String[] roles) {
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        user.setRoles(Arrays.stream(roles)
                .map(roleService::getRoleByName)
                .collect(Collectors.toList()));
        userDao.updateUser(id, user);
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
}