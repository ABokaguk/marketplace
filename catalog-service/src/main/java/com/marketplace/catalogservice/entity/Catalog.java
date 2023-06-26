package com.marketplace.catalogservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Сущность, Каталог, содержит в себе различные категории.
 * id - уникальный идентификатор
 * category - id fk (bigint) предмета, сущность Category, которая будет приходить извне
 * gender - id fk (bigint) предмета, сущность Gender, которая будет приходить извне
 * productName - название товара
 * price - цена предмета
 * isDiscounted - применена ли скидка
 * discountPrice - размер скидки
 */

@Entity
@Table(name = "CATALOG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catalog {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotNull
    @ManyToOne
    // Child (Many) - тк сущность Catalog имеет Внешние ключи (FK), тогда сущность Category является Parent(One)
    @JoinColumn(name = "category") // "id" - название колонки РК у сущности Category
    private Category category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "gender")  // "id" - название колонки РК у сущности Gender
    private Gender gender;

    @NotEmpty
    @Size(min = 2, max = 50, message = "Name should be 2 and 50 characters")
    @Column(name = "product_name")
    private String productName;

    @NotNull
    @Min(value = 0, message = "Price should be greater than 0")
    @Column(name = "price")
    private Long price;

    @NotNull
    @Column(name = "is_discounted")
    private Boolean isDiscounted;

    @Min(value = 0, message = "discount price should be greater than 0")
    @Column(name = "discount_price")
    private Long discountPrice;
}