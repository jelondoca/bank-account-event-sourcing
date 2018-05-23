package com.sergio.services.producers.serializers;

import com.sergio.model.events.external.AccountKafkaEvent;
import com.sergio.services.producers.config.JsonConfigFactory;
import org.apache.kafka.common.serialization.Serializer;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.nio.charset.Charset;
import java.util.Map;

public class KafkaOrderSerializer implements Serializer<AccountKafkaEvent> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, AccountKafkaEvent event) {
        Jsonb json = JsonbBuilder.create(JsonConfigFactory.config());
        String serialized = json.toJson(event, event.getClass().getSuperclass());
        return serialized.getBytes(Charset.defaultCharset());
    }

    @Override
    public void close() {

    }

}
