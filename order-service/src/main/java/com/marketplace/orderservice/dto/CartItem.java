package com.marketplace.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {
    private UUID itemId;
    private Integer count;
    private Long price;
    private Boolean isDiscounted;
    private Long discountPrice;
}
