package com.marketplace.orderservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

/**
 * Сущность, Заказ.
 * id - Уникальный идентификатор заказа
 * state - Состояние заказа
 * client_id - Уникальный идентификатор пользователя
 * receiving_method - Способ получения
 * delivery_address - Адрес доставки
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
@Schema(description = "Заказ")
public class Order {


    @Id
    @Column(name = "id")
    @Schema(example = "123e4567-e89b-12d3-a436-426614174000", description = "Уникальный идентификатор заказа")
    private UUID id;

    @Column(name = "state")
    @Schema(example = "paid", description = "Статус заказа")
    private String state;

    @Column(name = "client_id")
    @Schema(example = "123e4567-e89b-12d3-a436-426614174000", description = "Уникальный идентификатор пользователя")
    private UUID clientId;

    @Column(name = "receiving_method")
    @Schema(example = "delivery", description = "Способ получения")
    private String receivingMethod;

    @Column(name = "delivery_address")
    @Schema(example = "123e4567-e89b-12d3-a436-426614174000", description = "Адрес доставки")
    private UUID deliveryAddress;
}