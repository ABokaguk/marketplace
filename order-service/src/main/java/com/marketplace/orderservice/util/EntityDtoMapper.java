package com.marketplace.orderservice.util;

import com.marketplace.orderservice.dto.OrderItemDto;
import com.marketplace.orderservice.dto.OrderListDto;
import com.marketplace.orderservice.entity.OrderItem;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Класс для конвертации сущностей в дто*/
@Service
@AllArgsConstructor
public class EntityDtoMapper {
    private final ModelMapper modelMapper;

    //Конвертация товара клиента в Дто
    public OrderItemDto orderItemToDto(OrderItem orderItem) {
        return modelMapper.map(orderItem, OrderItemDto.class);
    }
    //Конвертация Дто в товар клиента
    public OrderItem dtoToOrderItem(OrderItemDto orderItemDto) {
        return modelMapper.map(orderItemDto, OrderItem.class);
    }

    //Конвертация товара клиента в Дто список товаров клиента
    public OrderItem orderItemToDtoList(OrderListDto orderListDto) {
        return modelMapper.map(orderListDto, OrderItem.class);
    }

}
