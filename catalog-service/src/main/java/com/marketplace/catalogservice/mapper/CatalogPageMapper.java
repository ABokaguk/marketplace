package com.marketplace.catalogservice.mapper;

import com.marketplace.catalogservice.dto.CatalogPage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogPageMapper {

    CatalogPage toDto(Integer pageNumber, Integer pageSize);
}
