package com.marketplace.catalogservice.service;

import com.marketplace.catalogservice.CatalogServiceApplicationTest;
import com.marketplace.catalogservice.dto.ItemDto;
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
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CatalogServiceImplTest extends CatalogServiceApplicationTest {

    @MockBean
    private CatalogRepository catalogRepositoryMock;
    @Autowired
    private CatalogRepository catalogRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private CatalogServiceImpl catalogServiceImpl;

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
    void getCatalogByIdTest() {
        UUID itemId = UUID.randomUUID();
        ItemDto itemDto = new ItemDto();
        Catalog catalog1 = new Catalog();
        when(catalogRepositoryMock.findById(itemId)).thenReturn(Optional.of(catalog1));
        ItemDto result = catalogServiceImpl.getCatalogById(itemId);
        assertEquals(itemDto, result);
    }

    @Test
    void addCatalogTest() {
        Boolean resultSave = catalogServiceImpl.addCatalog(catalog);
        assertEquals(true, resultSave);
    }

    @AfterEach
    void tearDown() {
        catalogRepository.delete(catalog);
        categoryRepository.delete(category);
        genderRepository.delete(gender);
    }
}