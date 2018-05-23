package com.sergio.services.consumer.deserializers;

import com.sergio.model.events.external.AccountKafkaEvent;
import com.sergio.services.producers.config.JsonConfigFactory;
import org.apache.kafka.common.serialization.Deserializer;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.Map;

public class KafkaOrderDeserializer implements Deserializer<AccountKafkaEvent> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public AccountKafkaEvent deserialize(String topic, byte[] data) {
        try {
            String payload = new String(data);
            System.out.println("payload = " + payload);
            Jsonb builder = JsonbBuilder.create(JsonConfigFactory.config());
            AccountKafkaEvent event = builder.fromJson(payload, AccountKafkaEvent.class);
            System.out.println("event = " + event);
            return event;
        }catch (Exception e) {
            System.out.println("e = " + e);
            throw e;
        }
    }

    @Override
    public void close() {

    }

}
