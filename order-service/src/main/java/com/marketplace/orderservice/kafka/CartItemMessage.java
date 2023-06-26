package com.marketplace.orderservice.kafka;

import com.marketplace.orderservice.dto.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Profile("testKafka")
public class CartItemMessage {
    private UUID clientId;
    private List<CartItem> cartItemList;
}
