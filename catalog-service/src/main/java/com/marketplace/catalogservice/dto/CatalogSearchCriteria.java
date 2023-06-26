package com.marketplace.catalogservice.dto;

import lombok.Data;

@Data
public class CatalogSearchCriteria {

    private Long minPrice;

    private Long maxPrice;

    private Long gender;

    private Long category;

    private Boolean isDiscounted;
}
