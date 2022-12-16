package com.example.clothingshop.controllers;

import com.example.clothingshop.models.User;
import com.example.clothingshop.services.CartService;
import com.example.clothingshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Column;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final UserService userService;


    @PostMapping("/cart/add/{productId}")
    public String cart(@PathVariable Long productId, @RequestParam int amount, Principal principal){
        cartService.addProduct(productId, amount, userService.getUserByPrincipal(principal).getId());
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String cart(Principal principal, Model model){
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("cartPairs", cartService.getCartPairs(user.getId()));
        model.addAttribute("totalPrice", cartService.getTotalPrice(user.getId()));
        model.addAttribute("user", user);
        return "cart";
    }

}
