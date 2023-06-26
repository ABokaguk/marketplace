package com.marketplace.catalogservice.dto;

import lombok.Data;

@Data
public class CatalogPage {

    private Integer pageNumber;

    private Integer pageSize;
}