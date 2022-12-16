package com.example.clothingshop.services;

import com.example.clothingshop.models.Image;
import com.example.clothingshop.models.Product;
import com.example.clothingshop.models.User;
import com.example.clothingshop.repositories.ProductRepository;
import com.example.clothingshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private long id;



    public List<Product> listProducts(){
        return productRepository.findAll();
    }

    public List<Product> listProducts(String gender, String category){

        if ((gender == null || gender.equals("-1") ) && (category == null || category.equals("-1")))
            return listProducts();

        List<Product> productList = productRepository.findAll();
        List<Product> productsForOutput = new ArrayList<>();

        Iterator iterator = productList.iterator();
        while (iterator.hasNext()){
            Product product = (Product) iterator.next();
            if (gender.equals("-1")){
                if (product.getCategory().equals(category)){
                    productsForOutput.add(product);
                }

            }
            else if (gender.equals("0")){
                if ((product.getCategory().equals(category) || category.equals("-1")) && !product.isGender())
                    productsForOutput.add(product);
            }
            else if (gender.equals("1")){
                if ((product.getCategory().equals(category) || category.equals("-1"))&& product.isGender())
                    productsForOutput.add(product);
            }
        }
        return productsForOutput;
    }

    public void saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3,
                            boolean gender, String category) throws IOException {
        product.setGender(gender);
        product.setCategory(category);

        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }

        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);
    }

    public void deleteProduct(Long id){
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public List<Product> getProductsByIds(List<Long> ids){
        return productRepository.findAllById(ids);
    }

    public void changeProductAmount(Long productId, int addValue){
        int amount = getProductById(productId).getAmount();
        //Objects.requireNonNull(productRepository.findById(productId).orElse(null)).setAmount(amount + addValue);
        Product product = getProductById(productId);
        product.setAmount(amount + addValue);
        productRepository.save(product);
    }
}
