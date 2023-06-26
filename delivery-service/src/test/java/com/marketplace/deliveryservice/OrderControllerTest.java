package com.marketplace.deliveryservice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.deliveryservice.entity.Client;
import com.marketplace.deliveryservice.entity.Courier;
import com.marketplace.deliveryservice.entity.Order;
import com.marketplace.deliveryservice.repository.ClientRepository;
import com.marketplace.deliveryservice.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.time.OffsetDateTime;
import java.util.UUID;


@AutoConfigureMockMvc
@SpringBootTest
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientRepository clientRepository;

    private Client client;
    private Order order;
    private Courier courier;
    @BeforeEach
    public void init() {
        client = new Client(UUID.randomUUID(),"Olya", "00000");
        clientRepository.save(client);

        order = new Order(UUID.randomUUID(),"accepted", client, UUID.randomUUID(), 2, OffsetDateTime.parse("2021-12-31T23:00:00.000+00:00"));
        orderRepository.save(order);
    }
    @Test
    void showAllOrderItemsTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        mockMvc.perform(MockMvcRequestBuilders.get("/order/{id}",client.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(order.getId().toString()));
    }
    @AfterEach
    void deleteAfterTest() {

        orderRepository.deleteById(order.getId());
        clientRepository.delete(client);
    }

}
