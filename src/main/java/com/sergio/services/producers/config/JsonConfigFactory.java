package com.sergio.services.producers.config;

import com.sergio.services.consumer.deserializers.JSONBOrderDeserializer;
import com.sergio.services.producers.serializers.JSONBOrderSerializer;

import javax.json.bind.JsonbConfig;

public class JsonConfigFactory {
    public static JsonbConfig config() {
        JsonbConfig config = new JsonbConfig();
        config.withSerializers(new JSONBOrderSerializer());
        config.withDeserializers(new JSONBOrderDeserializer());
        return config;
    }
}
