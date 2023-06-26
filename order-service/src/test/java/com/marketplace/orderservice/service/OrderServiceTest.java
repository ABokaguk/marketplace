package com.marketplace.orderservice.service;

import com.marketplace.orderservice.OrderServiceApplication;
import com.marketplace.orderservice.entity.OrderItem;
import com.marketplace.orderservice.exception.ChangeStateException;
import com.marketplace.orderservice.exception.OrderNotFoundException;
import com.marketplace.orderservice.exception.StateNotFoundException;
import com.marketplace.orderservice.repository.OrderItemRepository;
import com.marketplace.orderservice.services.OrderItemService;
import com.marketplace.orderservice.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@Testcontainers
@DirtiesContext
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.liquibase.enabled=true"})
@ContextConfiguration(classes = OrderServiceApplication.class)
public class OrderServiceTest {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13.2")
            .withDatabaseName("postgres")
            .withPassword("admin")
            .withUsername("postgres");

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }

    @Autowired
    private OrderItemService orderItemService;
    @MockBean
    private OrderItemRepository orderItemRepository;

    private UUID idCorrect;
    private UUID idIncorrect;
    private UUID idForChangeStateException;
    private OrderItem orderItem;
    private final String STATE_CORRECT = "ON_SHIPPING";
    private final String STATE_INCORRECT = "FLYING_TO_THE_MOON";

    @BeforeEach
    public void setup() {
        orderItem = new OrderItem();
        idCorrect = UUID.fromString("6c7d56dd-7c85-42b2-8086-a0d37550e08f");
        idIncorrect = UUID.fromString("a95bc2e6-fe15-485e-ba61-a929a7c5761a");
        idForChangeStateException = UUID.fromString("283cc8a4-c188-49ce-8e4a-31a9821f9973");
        //for correct execution
        when(orderItemRepository.findById(idCorrect)).thenReturn(Optional.of(orderItem));
        when(orderItemRepository.isExistsOrderItemByIdAndState(idCorrect,STATE_CORRECT)).thenReturn(true);
        //for OrderNotFoundException case
        when(orderItemRepository.findById(idIncorrect)).thenReturn(Optional.empty());
        //for ChangeStateException case
        when(orderItemRepository.findById(idForChangeStateException)).thenReturn(Optional.of(orderItem));
        when(orderItemRepository.isExistsOrderItemByIdAndState(idForChangeStateException,STATE_CORRECT)).thenReturn(false);
    }

    @Test
    void changeStateShouldBeExecutedCorrectly(){
        assertAll(()-> orderItemService.changeState(idCorrect, STATE_CORRECT));
    }

    @Test
    void changeStateShouldThrowOrderNotFoundException(){
        assertThrows(OrderNotFoundException.class,() -> orderItemService.changeState(idIncorrect, STATE_CORRECT),
                "OrderNotFoundException expected to be thrown, but it didn't");
    }

    @Test
    void changeStateShouldThrowChangeStateException(){
        assertThrows(ChangeStateException.class,() -> orderItemService.changeState(idForChangeStateException, STATE_CORRECT),
                "ChangeStateException expected to be thrown, but it didn't");
    }

    @Test
    void changeStateShouldThrowStateNotFoundException(){
        assertThrows(StateNotFoundException.class,() -> orderItemService.changeState(idCorrect, STATE_INCORRECT),
                "StateNotFoundException expected to be thrown, but it didn't");
    }

}
