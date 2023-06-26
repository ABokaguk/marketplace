package com.marketplace.deliveryservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Дто класс для таблицы DeliveryItem
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Заказ")
public class ItemDto {
    private UUID id;
    private String state;
    private UUID deliveryAddress;
    private Integer count;
    private OffsetDateTime dateTime;
}
