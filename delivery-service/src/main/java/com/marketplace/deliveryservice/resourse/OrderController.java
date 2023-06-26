package com.marketplace.deliveryservice.resourse;

import com.marketplace.deliveryservice.api.resource.OrderResource;
import com.marketplace.deliveryservice.dto.ItemDto;
import com.marketplace.deliveryservice.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Класс контроллер
 * */
@Slf4j
@AllArgsConstructor
@RestController
public class OrderController implements OrderResource {
    private final OrderService orderService;

    //обрабатываем get запрос /order/{id}.получаем лист товаров,конвертируем все товары в дто и возвращаем
    @Override
    public List<ItemDto> showAllOrderItems(UUID id) {
        List<ItemDto> orderItemList = new ArrayList<>();
        orderService.showAllOrderItems(id);
        return  orderItemList;
    }
}
