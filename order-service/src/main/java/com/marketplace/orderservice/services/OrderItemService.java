package com.marketplace.orderservice.services;

import com.marketplace.orderservice.entity.OrderItem;
import com.marketplace.orderservice.exception.ChangeStateException;
import com.marketplace.orderservice.exception.OrderNotFoundException;
import com.marketplace.orderservice.exception.StateNotFoundException;
import com.marketplace.orderservice.repository.OrderItemRepository;
import com.marketplace.orderservice.util.OrderStates;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public void addAllOrderItems(List<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems);
    }
    //Получение всех товаров по id клиента
    @Transactional(readOnly = true)
    public List<OrderItem> showAllOrderItems(UUID id){
       return orderItemRepository.findAllByClientId(id);

    }


    /**
     * Метод меняет статус заказа на заданный
     */
    public void changeState(UUID id, String state){
        try {
            OrderStates.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new StateNotFoundException(state);
        }
        OrderItem item = orderItemRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        item.setState(state);
        orderItemRepository.saveAndFlush(item);
        if (!orderItemRepository.isExistsOrderItemByIdAndState(id,state)){
            throw new ChangeStateException(id);
        }
    }
}
