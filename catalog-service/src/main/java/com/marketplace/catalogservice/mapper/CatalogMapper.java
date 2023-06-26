package com.marketplace.catalogservice.mapper;

import com.marketplace.catalogservice.dto.CatalogDTO;
import com.marketplace.catalogservice.dto.ItemDTO;
import com.marketplace.catalogservice.entity.Catalog;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CatalogMapper {

    private final ModelMapper modelMapper;

    /**
     * Метод для конвертации каталога в ДТО
     *
     * @param catalog
     * @return CatalogDto
     */
    public CatalogDTO catalogToDTO(Catalog catalog) {
        return modelMapper.map(catalog, CatalogDTO.class);
    }

    //    Метод для конвертации ДТО в каталог
    public Catalog dtoToCatalog(CatalogDTO catalogDTO) {
        return modelMapper.map(catalogDTO, Catalog.class);
    }

    /**
     * Метод для конвертации Catalog в ItemDTO
     *
     * @param catalog
     * @return ItemDTO
     */
    public ItemDTO itemToDTO(Catalog catalog) {
        return modelMapper.map(catalog, ItemDTO.class);
    }
}
