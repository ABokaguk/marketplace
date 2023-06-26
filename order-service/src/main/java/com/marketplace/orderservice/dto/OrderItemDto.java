package com.marketplace.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Дто класс для таблицы OrderItem
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {
    private UUID id;
    private Integer count;
    private Long price;
    private Long discountPrice;
    private String state;
}
