package com.marketplace.orderservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

/**
 * Сущность, Клиент.
 * id - уникальный идентификатор
 * name - имя
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENT")
@Schema(description = "Владелец заказа")
public class Client {
    @Id
    @Column(name = "id")
    @Schema(example = "123e4567-e89b-12d3-a436-426614174000", description = "Уникальный идентификатор пользователя")
    private UUID id;

    @Column(name = "name")
    @Schema(example = "alex", description = "Имя пользователя")
    private String name;
}
