package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
    }

    @GetMapping
    public String userPage(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id == null) {
            model.addAttribute("users", userService.findAll());
            return "users";
        }
        model.addAttribute("user", userService.findById(id).get());
        return "userPage";
    }
}
