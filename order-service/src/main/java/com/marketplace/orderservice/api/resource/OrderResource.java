package com.marketplace.orderservice.api.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tags(
        value = {
                @Tag(name = "Заказы", description = "Сервис для хранения и отслеживания заказов")
        }
)
@RestController
@RequestMapping("/order")
public interface OrderResource {

    @PutMapping("/change-state/{id}")
    @Operation(summary = "Изменение статуса заказа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Товар удален",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Возникла ошибка при удалении товара",
                    content = @Content)})
    ResponseEntity<HttpStatus> changeState(@PathVariable UUID id, @RequestParam String state);
}
