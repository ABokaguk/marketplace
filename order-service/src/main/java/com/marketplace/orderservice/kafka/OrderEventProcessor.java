package com.marketplace.orderservice.kafka;

import com.marketplace.orderservice.dto.CartItem;
import com.marketplace.orderservice.dto.ClientNotFoundException;
import com.marketplace.orderservice.entity.Client;
import com.marketplace.orderservice.entity.OrderItem;
import com.marketplace.orderservice.services.ClientService;
import com.marketplace.orderservice.services.OrderItemService;
import com.marketplace.orderservice.util.JsonMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("testKafka")
@AllArgsConstructor
@Slf4j
public class OrderEventProcessor {

    private final JsonMapper jsonMapper;
    private final OrderItemService orderItemService;
    private final ClientService clientService;

    // метод принимает сообщение из топика, распарсивает и сохраняет заказ в базе
    @KafkaListener(topics = "cart-service", groupId = "group_id")
    public void consume(String message) throws IOException, ClientNotFoundException {

//        распарсиваем сообщение из кафки в дто
        CartItemMessage cartItemMessage = jsonMapper.mapFromJson(message, CartItemMessage.class);

        log.info("Получено сообщение из топика, которое содержит список заказов клиента с ID: {}", cartItemMessage.getClientId());
        Client client = clientService.findClientById(cartItemMessage.getClientId());
        List<CartItem> cartItemList = cartItemMessage.getCartItemList();

//        преобразуем предметы корзины в предметы заказа + передаём туда клиента
        List<OrderItem> orderItemList = cartItemList.stream()
                .map(cartItem -> new OrderItem(null, client, cartItem.getCount(), cartItem.getPrice(), cartItem.getIsDiscounted(), cartItem.getDiscountPrice(), "accepted"))
                .collect(Collectors.toList());

//        сохраняем заказанные предметы в базе
        orderItemService.addAllOrderItems(orderItemList);
        log.info("Список заказов пользователя с ID: {} сохранён в базе", cartItemMessage.getClientId());
    }
}
