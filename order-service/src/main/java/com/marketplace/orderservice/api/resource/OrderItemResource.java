package com.marketplace.orderservice.api.resource;


import com.marketplace.orderservice.dto.OrderItemDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Tags(
        value = {
                @Tag(name = "Заказ", description = "Каталог товаров из заказа с его статусом")
        }
)
@RestController
@RequestMapping("/order")
public interface OrderItemResource {
    @GetMapping("/{id}")
    @Operation(summary = "Вывод всех товаров в заказе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ найден",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Заказ не найден",
                    content = @Content)})
    List<OrderItemDto> showAllOrderItems(@PathVariable @NotNull UUID id);

    @PutMapping("/change-state/{id}")
    @Operation(summary = "Изменение статуса заказа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Товар удален",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Возникла ошибка при удалении товара",
                    content = @Content)})
    ResponseEntity<HttpStatus> changeState(@PathVariable UUID id, @RequestParam String state);
}
