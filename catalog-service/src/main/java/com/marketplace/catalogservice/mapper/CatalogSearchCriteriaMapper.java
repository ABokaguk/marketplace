package com.marketplace.catalogservice.mapper;

import com.marketplace.catalogservice.dto.CatalogSearchCriteria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogSearchCriteriaMapper {

    CatalogSearchCriteria toDto(Long minPrice, Long maxPrice, Long gender, Long category, Boolean isDiscounted);
}
