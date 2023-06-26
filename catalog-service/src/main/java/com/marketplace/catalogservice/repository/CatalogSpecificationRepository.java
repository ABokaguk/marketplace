package com.marketplace.catalogservice.repository;

import com.marketplace.catalogservice.dto.CatalogSearchCriteria;
import com.marketplace.catalogservice.entity.Catalog;
import org.springframework.data.jpa.domain.Specification;

public interface CatalogSpecificationRepository {

    Specification<Catalog> getFilter(CatalogSearchCriteria catalogSearchCriteria);

    Specification<Catalog> filterByMinPrice(Long minPrice);

    Specification<Catalog> filterByMaxPrice(Long maxPrice);

    Specification<Catalog> filterByGender(Long gender);

    Specification<Catalog> filterByCategory(Long category);

    Specification<Catalog> filterByIsDiscounted(Boolean isDiscounted);
}
