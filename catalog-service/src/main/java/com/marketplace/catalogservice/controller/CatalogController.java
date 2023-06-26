package com.marketplace.catalogservice.controller;

import com.marketplace.catalogservice.api.resource.CatalogResource;
import com.marketplace.catalogservice.dto.CatalogPage;
import com.marketplace.catalogservice.dto.CatalogSearchCriteria;
import com.marketplace.catalogservice.dto.ItemDTO;
import com.marketplace.catalogservice.entity.Catalog;
import com.marketplace.catalogservice.service.CatalogService;
import com.marketplace.catalogservice.mapper.CatalogMapper;
import com.marketplace.catalogservice.mapper.CatalogPageMapper;
import com.marketplace.catalogservice.mapper.CatalogSearchCriteriaMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class CatalogController implements CatalogResource {

    private final CatalogService catalogService;

    private final CatalogMapper catalogMapper;

    private final CatalogPageMapper catalogPageMapper;

    private final CatalogSearchCriteriaMapper catalogSearchCriteriaMapper;

    @Override
    public ItemDTO getItemById(UUID id) {
        return catalogService.getCatalogById(id);
    }

    @Override
    public ResponseEntity<Page<Catalog>> findAllWithFilters(Integer pageNumber, Integer pageSize, Long minPrice, Long maxPrice, Long gender, Long category, Boolean isDiscounted) {
        CatalogPage catalogPage = catalogPageMapper.toDto(pageNumber, pageSize);
        CatalogSearchCriteria catalogSearchCriteria = catalogSearchCriteriaMapper.toDto(minPrice, maxPrice, gender, category, isDiscounted);
        Page<Catalog> catalogWithFilters = catalogService.findAllWithFilters(catalogPage, catalogSearchCriteria);
        return ResponseEntity.ok().body(catalogWithFilters);
    }

    @Override
    public ResponseEntity<Object> createCatalog(com.marketplace.catalogservice.dto.CatalogDTO catalogDTO) {
        catalogService.addCatalog(catalogMapper.dtoToCatalog(catalogDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}