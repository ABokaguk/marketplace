package com.marketplace.catalogservice.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.catalogservice.CatalogServiceApplicationTest;
import com.marketplace.catalogservice.entity.Catalog;
import com.marketplace.catalogservice.entity.Category;
import com.marketplace.catalogservice.entity.Gender;
import com.marketplace.catalogservice.repository.CatalogRepository;
import com.marketplace.catalogservice.repository.CategoryRepository;
import com.marketplace.catalogservice.repository.GenderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityManager;
import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CatalogControllerTest extends CatalogServiceApplicationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private CatalogRepository catalogRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    EntityManager entityManager;
    private Catalog catalog;
    private Category category;
    private Gender gender;

    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setCategoryName("boots");
        categoryRepository.save(category);

        gender = new Gender();
        gender.setGenderName("male");
        genderRepository.save(gender);

        catalog = new Catalog();
        catalog.setCategory(category);
        catalog.setGender(gender);
        catalog.setProductName("shoes");
        catalog.setPrice(200L);
        catalog.setIsDiscounted(true);
        catalog.setDiscountPrice(190L);
        catalogRepository.save(catalog);
    }

    @Test
    void getCatalogByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/catalog/{id}", catalog.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(catalog.getId().toString()));

    }

    @Test
    void showAllCatalogTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/catalog/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(catalog.getId().toString()));
    }

    @Test
    void createCatalogTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/catalog/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(catalog))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @AfterEach
    void tearDown() {
        catalogRepository.delete(catalog);
        categoryRepository.delete(category);
        genderRepository.delete(gender);
    }
}