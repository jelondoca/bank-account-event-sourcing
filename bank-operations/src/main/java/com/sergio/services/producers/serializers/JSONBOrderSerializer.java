package com.sergio.services.producers.serializers;

import com.sergio.model.events.Order;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

public class JSONBOrderSerializer implements JsonbSerializer<Order> {
    @Override
    public void serialize(Order event, JsonGenerator generator, SerializationContext ctx) {
        generator.writeStartObject();
        ctx.serialize(event.getClass().getSimpleName(), event, generator);
        generator.writeEnd();
    }
}