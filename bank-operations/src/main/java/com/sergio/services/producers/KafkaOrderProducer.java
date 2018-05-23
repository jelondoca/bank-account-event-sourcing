package com.sergio.services.producers;

import com.sergio.model.events.Order;
import com.sergio.services.EventBus;
import com.sergio.services.producers.serializers.KafkaOrderSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaOrderProducer implements EventBus {

    public void produce(Order order) {
        // set kafka as message broker
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaOrderSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "1"); // wait leader broker to get response

        try (Producer<String, Order> producer = new KafkaProducer<>(props)) {
            // sync fashion
            Future<RecordMetadata> record = producer.send(new ProducerRecord<>("test", order));
            record.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
