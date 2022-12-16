package com.example.clothingshop.tests;


import com.example.clothingshop.models.Product;
import com.example.clothingshop.models.User;
import com.example.clothingshop.repositories.ProductRepository;
import com.example.clothingshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
public class RepositoryTest {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void userAddDelete(){
        User user = new User();
        user.setName("Nastia");
        user.setPassword("1111");
        user.setEmail("Vova@mail.ru");
        user.setPhoneNumber("12354");
        long id =userRepository.save(user).getId();
        userRepository.deleteById(id);
    }

    @Test
    public void productAddDelete(){

        Product product = new Product();
        product.setTitle("TestProduct");
        long id = productRepository.save(product).getId();
        productRepository.deleteById(id);
    }
}
