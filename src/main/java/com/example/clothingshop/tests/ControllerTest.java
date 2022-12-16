package com.example.clothingshop.tests;

import com.example.clothingshop.controllers.ProductController;
import com.example.clothingshop.models.Product;
import com.example.clothingshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/*@RunWith(SpringRunner.class)*/
@SpringBootTest
@RequiredArgsConstructor
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAdminPages1() throws Exception {
        this.mockMvc.perform(get("/admin/users")).andDo(print()).
                andExpect(status().is3xxRedirection());
    };

    @Test
    public void testAdminPages2() throws Exception {
        this.mockMvc.perform(get("/admin/products")).andDo(print()).
                andExpect(status().is3xxRedirection());
    };

    @Test
    public void testAdminPages3() throws Exception {
        this.mockMvc.perform(get("/admin/user/edit")).andDo(print()).
                andExpect(status().is3xxRedirection());
    };

    @Test
    public void testAdminPages4() throws Exception {
        this.mockMvc.perform(get("/admin/user/edit")).andDo(print()).
                andExpect(status().is3xxRedirection());
    };

    @Test
    public void testAdminPages5() throws Exception {
        this.mockMvc.perform(get("/user/1")).andDo(print()).
                andExpect(status().is3xxRedirection());
    };

    @Test
    public void testAdminPages6() throws Exception {
        this.mockMvc.perform(get("/product/add")).andDo(print()).
                andExpect(status().is3xxRedirection());
    };

    @Test
    public void nonAuthorize() throws Exception {
        this.mockMvc.perform(get("/profile")).andDo(print()).
                andExpect(status().is3xxRedirection());
    };

    @Test
    public void nonAuthorize2() throws Exception {
        this.mockMvc.perform(get("/cart")).andDo(print()).
                andExpect(status().is3xxRedirection());
    };


}
