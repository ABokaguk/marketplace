package com.marketplace.deliveryservice.util;

import com.marketplace.deliveryservice.dto.ItemDto;
import com.marketplace.deliveryservice.entity.Order;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-29T05:15:39+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public ItemDto orderItemToDto(Order orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        ItemDto.ItemDtoBuilder itemDto = ItemDto.builder();

        itemDto.id( orderItem.getId() );
        itemDto.state( orderItem.getState() );
        itemDto.deliveryAddress( orderItem.getDeliveryAddress() );

        return itemDto.build();
    }
}
