package com.marketplace.catalogservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CATEGORY")
@Schema(description = "Категория продукта")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(example = "1", description = "ID категории")
    private Long id;

    @Column(name = "category_name")
    @NotEmpty
    @Schema(example = "boots", description = "Название категории")
    private String categoryName;
}
