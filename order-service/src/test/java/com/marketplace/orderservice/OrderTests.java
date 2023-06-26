package com.marketplace.orderservice;

import com.marketplace.orderservice.dto.CartItem;
import com.marketplace.orderservice.kafka.CartItemMessage;
import com.marketplace.orderservice.entity.Client;
import com.marketplace.orderservice.entity.OrderItem;
import com.marketplace.orderservice.kafka.OrderEventProcessor;
import com.marketplace.orderservice.repository.ClientRepository;
import com.marketplace.orderservice.repository.OrderItemRepository;
import com.marketplace.orderservice.util.JsonMapper;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@EmbeddedKafka
@Testcontainers
@SpringBootTest(properties = "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}")
@TestPropertySource(properties = {"spring.liquibase.enabled=true"})
class OrderTests {

//    ������ postgres ���� ���������
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13.2")
            .withDatabaseName("postgres")
            .withPassword("admin")
            .withUsername("postgres");

//    ����� � ��������� postgres ��������� ���� ��������� �� ������
    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }

//    ������ kafka ���� ���������(����� ������ ��� ������ ���������)
//    @Container
//    public static KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

//    ����� � ��������� kafka ���� ��������� ���� ��������� �� ������
//    @DynamicPropertySource
//    static void kafkaProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
//    }

//    ������� ����������� ����
    @Autowired
    private JsonMapper jsonMapper;
    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ClientRepository clientRepository;
    @SpyBean
    private OrderEventProcessor processor;

//    ������� ����������� ����, � ��� �� ����� ��������� � ����������
    private Producer<String, String> producer;
//    private Consumer<String, String> consumer;
    private final String TOPIC_NAME = "cart-service";
    private CartItemMessage cartItemMessage;
    private CartItem cartItem;
    private UUID uuid = UUID.randomUUID();
    @Captor
    ArgumentCaptor<String> messageArgumentCaptor;

//    ���� ����� ����������� ����� �������
    @BeforeEach
    public void setUp() {
//        ��������� ����
        cartItem = new CartItem(uuid, 1, 3333L, true, 2000L);
        List<CartItem> list = new ArrayList<>();
        list.add(cartItem);
        cartItemMessage = new CartItemMessage(uuid, list);

//        ����������� ���������
        Map<String, Object> configs = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
        producer = new DefaultKafkaProducerFactory<>(configs, new StringSerializer(), new StringSerializer()).createProducer();

//        ����������� ����������
//        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("group_id", "true", embeddedKafkaBroker);
//        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        consumer = new DefaultKafkaConsumerFactory<String, String>(consumerProps)
//                .createConsumer();

//        ����������� ���������� �� ���������
//        consumer.subscribe(Collections.singleton(TOPIC_NAME));
    }

//    ����� ����������� ����� ���������� ������
    @AfterEach
    public void setDown() {
        producer.close();
//        consumer.close();
    }

//        ���� ������ ������ �� ���� ��������� �� �����
    @Test
    void orderEventProcessorTest() throws Exception {

//        ������ � ���������� ���������, ����� ���� ��������� � json ��� cartItemMessage
        producer.send(new ProducerRecord<>(TOPIC_NAME, 0, "1111", jsonMapper.mapToJson(cartItemMessage)));
        producer.flush();

        verify(processor, timeout(5000).times(1)).consume(messageArgumentCaptor.capture());
        CartItemMessage cartItemMessage1 = jsonMapper.mapFromJson(messageArgumentCaptor.getValue(), CartItemMessage.class);
        assertThat(cartItemMessage1).isEqualTo(cartItemMessage);

    }

//        ���� ������ ������ �� ������� ���������� �������� � ����
    @Test
    void orderEventProcessorTest_to_save() throws Exception {
        clientRepository.save(new Client(uuid, null));
        processor.consume(jsonMapper.mapToJson(cartItemMessage));

        List<OrderItem> orderItems = orderItemRepository.findAll();
        assertThat(orderItems.get(0).getPrice()).isEqualTo(3333L);
    }


//    @Test
//    void consumerTest() throws Exception {

//        �������� ���������
//        ConsumerRecord<String, String> singleRecord = KafkaTestUtils.getSingleRecord(consumer, TOPIC_NAME);

//        ���� ��������� ���������
//        assertThat(singleRecord).isNotNull();
//        assertThat(singleRecord.value()).isEqualTo(jsonMapper.mapToJson(cartItemMessage));
//    }
}