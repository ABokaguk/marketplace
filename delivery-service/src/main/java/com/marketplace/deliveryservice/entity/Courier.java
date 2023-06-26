package com.marketplace.deliveryservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;


/**
 * Сущность, Курьер.
 * id - уникальный идентификатор
 * name - имя
 * phone_number - номер телефона
 * city - город
 * delivery_count - оплата доставки
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courier")
@Schema(description = "Курьер")
public class Courier {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "phone_number")
    @NotNull
    private String phoneNumber;

    @Column(name = "city")
    @NotNull
    private String city;

    @Column(name = "delivery_count")
    @NotNull
    private Long deliveryCount;
}
