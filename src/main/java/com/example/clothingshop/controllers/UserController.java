package com.example.clothingshop.controllers;

import com.example.clothingshop.models.User;
import com.example.clothingshop.repositories.UserRepository;
import com.example.clothingshop.services.OrderService;
import com.example.clothingshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final OrderService orderService;


    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "login";
    }


    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal){
        model.addAttribute(userService.getUserByPrincipal(principal));
        return "profile";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/user/{id}")
    public String getUserInfo(@PathVariable Long id, Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        User client = userService.getUserById(id);
        model.addAttribute("client", client);
        model.addAttribute("orders", orderService.getOrders(client));
        return "/user-info";
    }



}
