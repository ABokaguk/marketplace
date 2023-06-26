package com.marketplace.orderservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Сущность, Предмет в заказе.
 * id - уникальный идентификатор
 * client_id - уникальный идентификатор клиента оформившего заказ
 * count - количество предметов
 * price - цена предмета
 * isDiscounted - применена ли скидка
 * discountPrice - размер скидки
 * state - статус заказа
 */

@Entity
@Table(name = "ORDER_ITEM")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {


    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    @NotNull
    @Column(name = "count")
    private Integer count;

    @NotNull
    @Column (name = "price")
    private Long price;

    @Column (name = "is_discounted")
    private Boolean isDiscounted;

    @Column (name = "discount_price")
    private Long discountPrice;

    @Column(name = "state")
    private String state;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(UUID id, Client client, Integer count, Long price, Boolean isDiscounted, Long discountPrice, String state) {
        this.id = id;
        this.client = client;
        this.count = count;
        this.price = price;
        this.isDiscounted = isDiscounted;
        this.discountPrice = discountPrice;
        this.state = state;
    }
}