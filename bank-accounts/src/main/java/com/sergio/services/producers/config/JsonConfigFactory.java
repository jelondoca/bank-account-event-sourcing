package com.sergio.services.producers.config;

import com.sergio.services.consumer.deserializers.JSONBAccountEventDeserializer;
import com.sergio.services.producers.serializers.JSONBAccountEventSerializer;

import javax.json.bind.JsonbConfig;

public class JsonConfigFactory {
    public static JsonbConfig config() {
        JsonbConfig config = new JsonbConfig();
        config.withSerializers(new JSONBAccountEventSerializer());
        config.withDeserializers(new JSONBAccountEventDeserializer());
        return config;
    }
}
