package com.marketplace.catalogservice.dto;

import com.marketplace.catalogservice.entity.Category;
import com.marketplace.catalogservice.entity.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Товар из каталога")
public class ItemDTO {

    @Schema(example = "123e4567-e89b-12d3-a456-426614174000", description = "Идентификатор товара")
    private UUID id;

    @NotNull
    @Schema(example = "Категория", description = "Категория товара")
    private Category category;

    @NotNull
    @Schema(example = "Гендер", description = "Гендерная принадлежность продукта")
    private Gender gender;

    @NotEmpty
    @Size(min = 2, max = 50)
    @Schema(example = "Куртка", description = "Название продукта")
    private String productName;

    @NotNull
    @Min(value = 0)
    @Schema(example = "5000", description = "Цена продукта")
    private Long price;

    @NotNull
    @Schema(example = "да", description = "Наличие скидки на продукт")
    private Boolean isDiscounted;

    @Min(value = 0)
    @Schema(example = "4000", description = "Цена продукта с учетом скидки")
    private Long discountPrice;
}