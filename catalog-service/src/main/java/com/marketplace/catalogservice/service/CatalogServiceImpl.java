package com.marketplace.catalogservice.service;

import com.marketplace.catalogservice.dto.CatalogPage;
import com.marketplace.catalogservice.dto.CatalogSearchCriteria;
import com.marketplace.catalogservice.dto.ItemDTO;
import com.marketplace.catalogservice.entity.Catalog;
import com.marketplace.catalogservice.exception.ItemNotFoundException;
import com.marketplace.catalogservice.mapper.CatalogMapper;
import com.marketplace.catalogservice.repository.CatalogSpecificationRepository;
import com.marketplace.catalogservice.repository.CatalogRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    private final CatalogSpecificationRepository catalogSpecificationRepository;

    private final CatalogMapper catalogMapper;

    @Override
    public ItemDTO getCatalogById(UUID id) {
        return catalogRepository.findById(id)
                .map(catalogMapper::itemToDTO)
                .orElseThrow(() -> {
                    log.error("Каталог с таким ID: {} не найден", id);
                    throw new ItemNotFoundException("Товар c id " + id + " не найден");
                });
    }

    @Override
    public Page<Catalog> findAllWithFilters(CatalogPage catalogPage, CatalogSearchCriteria catalogSearchCriteria) {
        Pageable withPage = Pageable.ofSize(catalogPage.getPageSize()).withPage(catalogPage.getPageNumber());
        return catalogRepository.findAll(catalogSpecificationRepository.getFilter(catalogSearchCriteria), withPage);
    }

    @Override
    @Transactional
    public void addCatalog(Catalog catalog) {
        catalogRepository.save(catalog);
        log.info("Каталог {} добавлен", catalog);
    }
}