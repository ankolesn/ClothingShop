package com.example.clothingshop.services;

import com.example.clothingshop.emums.Role;
import com.example.clothingshop.models.Product;
import com.example.clothingshop.models.User;
import com.example.clothingshop.repositories.ProductRepository;
import com.example.clothingshop.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_ADMIN);
        userRepository.save(user);
        return true;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }


    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }


    public List<User> list() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void changeUserRoles(User user) {
        if(user.getRoles().contains(Role.ROLE_ADMIN)){
            user.getRoles().clear();
            user.getRoles().add(Role.ROLE_USER);
        }else {
            user.getRoles().clear();
            user.getRoles().add(Role.ROLE_ADMIN);
        }
        userRepository.save(user);
    }
}
