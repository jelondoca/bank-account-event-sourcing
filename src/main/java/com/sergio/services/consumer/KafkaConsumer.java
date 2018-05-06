package com.sergio.services.consumer;

import com.sergio.model.events.Order;
import com.sergio.services.AccountService;
import com.sergio.services.consumer.deserializers.KafkaOrderDeserializer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Collections;
import java.util.Properties;

@ApplicationScoped
public class KafkaConsumer {

    @Inject
    AccountService accountService;

    @Inject
    Event<Order> orderEvent;

    @Resource
    ManagedExecutorService executorService;

    public void consume(@Observes @Initialized(ApplicationScoped.class) Object init) {
        executorService.submit(() -> {

            Properties props = new Properties();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "first-consumer");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaOrderDeserializer.class.getName());
            Consumer<String, Order> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(props);
            consumer.subscribe(Collections.singletonList("test"));

            try {
                while (true) {
                    System.out.println("WAITING FOR CONSUMING ORDERS...");
                    ConsumerRecords<String, Order> records = consumer.poll(Long.MAX_VALUE);
                    for (ConsumerRecord<String, Order> record : records) {
                        Order order = record.value();
                        orderEvent.fire(order);
                    }
                }
            }finally {
                consumer.close();
            }

        });
    }

}
