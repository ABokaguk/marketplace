package com.marketplace.deliveryservice.service;
import com.marketplace.deliveryservice.dto.ItemDto;
import com.marketplace.deliveryservice.entity.Order;
import com.marketplace.deliveryservice.repository.OrderRepository;
import com.marketplace.deliveryservice.util.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    public void addAllOrder(List<Order> order) {
        orderRepository.saveAll(order);
    }
    @Transactional
    public List<ItemDto> showAllOrderItems(UUID id){
        List<ItemDto> list = new ArrayList<>();
        orderRepository.findAllByClientId(id).forEach(order ->  list.add(orderMapper.orderItemToDto(order)));
        return list;
    }
}
