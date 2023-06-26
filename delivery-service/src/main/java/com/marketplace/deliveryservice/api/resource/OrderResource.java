package com.marketplace.deliveryservice.api.resource;


import com.marketplace.deliveryservice.dto.ItemDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Tags(
        value = {
                @Tag(name = "Заказ", description = "Доставка товаров из заказа с его информацией")
        }
)
@RequestMapping("/order")
public interface OrderResource {
    @GetMapping("/{id}")
    @Operation(summary = "Получение информации о заказе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ найден",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Заказ не найден",
                    content = @Content)})
    List<ItemDto> showAllOrderItems(@PathVariable @NotNull UUID id);
}
