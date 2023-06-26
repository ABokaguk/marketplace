package com.marketplace.orderservice.repository;

import com.marketplace.orderservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    //Получение всех товаров по id клиента
    List<OrderItem> findAllByClientId(UUID id);
    /**
     * удаление товара из заказа по id
     * реализация изменена из-за ключевого слова "order" в sql*/
    @Modifying
    @Query(nativeQuery = true,value = "delete from order_item where id=:id")
    void deleteById(@Param("id") UUID id);

    @Query("select (count(o) > 0) from OrderItem o where o.id = ?1 and o.state = ?2")
    boolean isExistsOrderItemByIdAndState(UUID id, String state);
}
