package com.marketplace.deliveryservice.util;

import com.marketplace.deliveryservice.dto.ItemDto;;
import com.marketplace.deliveryservice.entity.Order;
import org.mapstruct.Mapper;


/**
 * Класс для конвертации сущностей в дто*/
@Mapper(componentModel = "spring")
public interface OrderMapper {
    //Конвертация заказа клиента в Дто
    ItemDto orderItemToDto(Order orderItem);

}
