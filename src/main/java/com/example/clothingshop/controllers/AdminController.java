package com.example.clothingshop.controllers;

import com.example.clothingshop.models.User;
import com.example.clothingshop.repositories.ProductRepository;
import com.example.clothingshop.services.ProductService;
import com.example.clothingshop.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Controller
@AllArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/admin/products")
    public String products(Model model, Principal principal){
        model.addAttribute("products", productService.listProducts());
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "admin/admin-products";
    }

    @GetMapping("/admin/users")
    public String users(Model model, Principal principal){
        model.addAttribute("users", userService.list());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin/admin-panel";
    }


    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user) {
        userService.changeUserRoles(user);
        return "redirect:/admin/users";
    }
}
