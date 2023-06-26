package com.marketplace.deliveryservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;


/**
 * Сущность, Клиент.
 * id - уникальный идентификатор
 * state - статус заказа
 * client_id - уникальный идентификатор клиента оформившего заказ
 * delivery_address - адрес доставки
 * item_count - количество предметов
 * delivery_time - время с часовым поясом
 */

@Entity
@Table(name = "mp_order")
@Schema(description = "Заказ")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "state")
    @NotNull
    private String state;

    @ManyToOne(targetEntity = Client.class)
    @JoinColumn(name = "client_id")
    @NotNull
    private Client client;

    @Column(name = "delivery_address")
    @NotNull
    private UUID deliveryAddress;

    @Column(name = "item_count")
    @NotNull
    private Integer itemCount;

    @Column(name = "delivery_time")
    @NotNull
    private OffsetDateTime deliveryTime;

}
