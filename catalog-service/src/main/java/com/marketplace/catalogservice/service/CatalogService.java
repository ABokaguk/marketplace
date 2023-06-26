package com.marketplace.catalogservice.service;

import com.marketplace.catalogservice.dto.CatalogPage;
import com.marketplace.catalogservice.dto.CatalogSearchCriteria;
import com.marketplace.catalogservice.dto.ItemDTO;
import com.marketplace.catalogservice.entity.Catalog;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CatalogService {

    ItemDTO getCatalogById(UUID id);

    Page<Catalog> findAllWithFilters(CatalogPage catalogPage, CatalogSearchCriteria catalogSearchCriteria);

    void addCatalog(Catalog catalog);
}