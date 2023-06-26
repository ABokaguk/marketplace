package com.marketplace.deliveryservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Сущность, Клиент.
 * id - уникальный идентификатор
 * name - имя
 * phone_number - Номер телефона
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
@Schema(description = "Владелец заказа")
public class Client {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "phone_number")
    @NotNull
    private String phoneNumber;

}

