package com.marketplace.orderservice.resource;

import com.marketplace.orderservice.api.resource.OrderItemResource;
import com.marketplace.orderservice.dto.OrderItemDto;
import com.marketplace.orderservice.services.OrderItemService;
import com.marketplace.orderservice.util.EntityDtoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 * Класс контроллер
 * */
@Slf4j
@AllArgsConstructor
@Controller
public class OrderController implements OrderItemResource {
    private final OrderItemService orderItemService;
    private final EntityDtoMapper entityDtoMapper;

    //обрабатываем get запрос /order/{id}.получаем лист товаров,конвертируем все товары в дто и возвращаем
    @Override
    public List<OrderItemDto> showAllOrderItems(UUID id) {
        List<OrderItemDto> orderItemList = new ArrayList<>();
        orderItemService.showAllOrderItems(id).forEach(item ->  orderItemList.add(entityDtoMapper.orderItemToDto(item)));
        return  orderItemList;
    }
    @Override
    public ResponseEntity<HttpStatus> changeState(@PathVariable UUID id, @RequestParam String state) {
        log.info("Received a request to change the order status with the id {} to \" {} \"", id, state);
        orderItemService.changeState(id, state);
        log.info("Order status with the id {} was successfully changed to \"{}\"", id, state);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
