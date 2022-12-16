package com.example.clothingshop.controllers;

import com.example.clothingshop.models.Order;
import com.example.clothingshop.models.User;
import com.example.clothingshop.services.OrderService;
import com.example.clothingshop.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/order")
    public String orders(Principal principal, Model model){
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user",userService.getUserByPrincipal(principal));
        model.addAttribute("orders", orderService.getOrders(user));
        return "/orders";
    }

    @GetMapping("/order/{id}")
    public String order(@PathVariable Long id ,Principal principal, Model model){
        Order order = orderService.getOrder(id);
        User user = userService.getUserByPrincipal(principal);
        if ((order.getUserId().equals(user.getId()) || user.getRoles().contains("ROLE_ADMIN")) && orderService.getOrder(id) != null) {
            model.addAttribute("user", user);
            model.addAttribute("cartPairs", orderService.getOrderCartPairs(id));
            model.addAttribute("order", order);
            return "order-info";
        }
        return "redirect:/";
    }

    @PostMapping("/order/add")
    public String addOrder(Principal principal){
        orderService.createOrder(userService.getUserByPrincipal(principal));
        return "redirect:/";
    }

    @GetMapping("/my/orders")
    public String getOrders(Principal principal, Model model){
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("orders", orderService.getOrders(user));
        return "/orders";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/admin/order/delete/{id}")
    public String delete(@PathVariable Long id){
        orderService.deleteOrder(id);
        return "redirect:/admin/orders";
    }

    @PostMapping("/my/order/delete/{id}")
    public String delete(@PathVariable Long id, Principal principal){
        orderService.deleteOrder(id, userService.getUserByPrincipal(principal));
        return "redirect:/my/orders";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin/orders")
    public String getAllOrders(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("orders", orderService.getOrders());
        return "/admin/admin-orders";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/order/status/{id}")
    public String changeStatus(@PathVariable Long id, Principal principal, Model model){
        orderService.changeStatus(id);
        return "redirect:/admin/orders";
    }


}
