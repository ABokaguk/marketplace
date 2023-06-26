package com.marketplace.orderservice.resourse;

import com.marketplace.orderservice.OrderServiceApplication;
import com.marketplace.orderservice.exception.ChangeStateException;
import com.marketplace.orderservice.exception.OrderNotFoundException;
import com.marketplace.orderservice.exception.StateNotFoundException;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@DirtiesContext
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.liquibase.enabled=true"})
@ContextConfiguration(classes = OrderServiceApplication.class)
public class OrderControllerTest {

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
    private MockMvc mvc;
    @MockBean
    private OrderItemService orderItemService;
    private UUID idCorrect;
    private UUID idIncorrect;
    private UUID idForChangeStateException;
    private final String STATE_CORRECT = "ON_SHIPPING";
    private final String STATE_INCORRECT = "FLYING_TO_THE_MOON";

    @BeforeEach
    public void setup() {
        idCorrect = UUID.fromString("6c7d56dd-7c85-42b2-8086-a0d37550e08f");
        idIncorrect = UUID.fromString("a95bc2e6-fe15-485e-ba61-a929a7c5761a");
        idForChangeStateException = UUID.fromString("283cc8a4-c188-49ce-8e4a-31a9821f9973");
        doNothing().when(orderItemService).changeState(idCorrect, STATE_CORRECT);
        doThrow(new OrderNotFoundException(idIncorrect)).when(orderItemService)
                .changeState(idIncorrect, STATE_CORRECT);
        doThrow(new ChangeStateException(idForChangeStateException)).when(orderItemService)
                .changeState(idForChangeStateException, STATE_CORRECT);
        doThrow(new StateNotFoundException(STATE_INCORRECT)).when(orderItemService)
                .changeState(idCorrect, STATE_INCORRECT);
    }

    @Test
    void changeStateTestShouldReturnOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/order/change-state/" + idCorrect + "?state=" + STATE_CORRECT))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void changeStateTestExpectedFailedToChangeStateException() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/order/change-state/" + idForChangeStateException + "?state=" + STATE_CORRECT))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    void changeStateTestExpectedOrderNotFoundException() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/order/change-state/" + idIncorrect + "?state=" + STATE_CORRECT))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void changeStateTestExpectedStateNotFoundException() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/order/change-state/" + idCorrect + "?state=" + STATE_INCORRECT))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
