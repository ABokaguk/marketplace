package com.marketplace.catalogservice.api.resource;

import com.marketplace.catalogservice.dto.CatalogDTO;
import com.marketplace.catalogservice.dto.ItemDTO;
import com.marketplace.catalogservice.entity.Catalog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@Tags(value = @Tag(name = "Каталог", description = "Каталог товаров с ценами и скидками"))
@RequestMapping("/catalog")
public interface CatalogResource {


    @GetMapping("/{id}")
    @Operation(summary = "Получение товара из каталога по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Товар найден",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemDTO.class))),
            @ApiResponse(responseCode = "400", description = "Предоставлен неверный идентификатор", content = @Content),
            @ApiResponse(responseCode = "404", description = "Товар по заданному идентификатору не найден", content = @Content)})
    ItemDTO getItemById(@Parameter(description = "Идентификатор счета для поиска") @PathVariable @NotNull UUID id);

    @GetMapping
    @Operation(summary = "Вывод всех элементов каталога в соответствии с фильтрами")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Элемент найден",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Неверный запрос", content = @Content),
            @ApiResponse(responseCode = "404", description = "Каталог не найден", content = @Content)})
    ResponseEntity<Page<Catalog>> findAllWithFilters(
            @Parameter(description = "Номер страницы") @RequestParam(value = "pageNumber") Integer pageNumber,
            @Parameter(description = "Количество элементов на странице") @RequestParam(value = "pageSize") Integer pageSize,
            @Parameter(description = "Минимальная цена") @RequestParam(value = "minPrice", required = false) Long minPrice,
            @Parameter(description = "Максимальная цена") @RequestParam(value = "maxPrice", required = false) Long maxPrice,
            @Parameter(description = "Пол") @RequestParam(value = "gender", required = false) Long gender,
            @Parameter(description = "Категория") @RequestParam(value = "category", required = false) Long category,
            @Parameter(description = "Наличие скидки") @RequestParam(value = "isDiscounted", required = false) Boolean isDiscounted
    );

    @PostMapping
    @Operation(summary = "Создание каталога")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Каталог создан", content = @Content(mediaType = "application/json"))})
    ResponseEntity<Object> createCatalog(@RequestBody CatalogDTO catalogDTO);
}