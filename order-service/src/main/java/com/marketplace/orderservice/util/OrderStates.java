package com.marketplace.orderservice.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStates {

    NEW("товар находится в корзине"),
    PAID("оплачен"),
    PREPARING("готовится к отправке"),
    ON_SHIPPING("в пути"),
    READY_FOR_DELIVERY("готов к доставке"),
    IN_DELIVERY("заказ у курьера (если доставка)"),
    DELIVERED("в отделении (если самовывоз)"),
    RECEIVED("получен");

    private final String description;

}
