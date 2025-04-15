package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showUsers(ModelMap model, Principal principal) {
        model.addAttribute("admin", userService.getUserByEmail(principal.getName()));
        model.addAttribute("users", userService.listUsers());
        return "admin";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam(name = "rolesList") String[] roles) {
        userService.add(user, roles);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable("id") Integer id,
                             @ModelAttribute("user") User user,
                             @RequestParam(name = "rolesList") String[] roles) {

        userService.updateUser(id, user, roles);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}