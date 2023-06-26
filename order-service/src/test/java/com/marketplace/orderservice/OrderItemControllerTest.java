package com.marketplace.orderservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.orderservice.entity.Client;
import com.marketplace.orderservice.entity.Order;
import com.marketplace.orderservice.entity.OrderItem;
import com.marketplace.orderservice.repository.ClientRepository;
import com.marketplace.orderservice.repository.OrderItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class OrderItemControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ClientRepository clientRepository;

    private Client client;
    private Order order;
    private OrderItem orderItem;

    @BeforeEach
    public void init() {
        client = new Client(UUID.randomUUID(),"Vasya");
        clientRepository.save(client);

        orderItem = new OrderItem(UUID.randomUUID(),client,2,300L,false,200L,"accepted");
        orderItemRepository.save(orderItem);
    }
    @Test
    void showAllOrderItemsTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.get("/order/{id}",client.getId())
                                              .accept(MediaType.APPLICATION_JSON)
                                               .content(objectMapper.writeValueAsString(orderItem)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value(orderItem.getId().toString()));
    }
    @AfterEach
    void deleteAfterTest() {

        orderItemRepository.deleteById(orderItem.getId());
        clientRepository.delete(client);
    }

}
