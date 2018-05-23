package com.sergio.services.producers.serializers;

import com.sergio.model.events.external.AccountKafkaEvent;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

public class JSONBAccountEventSerializer implements JsonbSerializer<AccountKafkaEvent> {
    @Override
    public void serialize(AccountKafkaEvent event, JsonGenerator generator, SerializationContext ctx) {
        generator.writeStartObject();
        ctx.serialize(event.getClass().getName(), event, generator);
        generator.writeEnd();
    }
}