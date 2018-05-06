package com.sergio.services.consumer.deserializers;

import com.sergio.model.Order;
import com.sergio.model.WithdrawOrderPlaced;
import com.sergio.services.producers.config.JsonConfigFactory;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import java.lang.reflect.Type;

public class JSONBOrderDeserializer implements JsonbDeserializer<Order> {

    @Override
    public Order deserialize(JsonParser parser, DeserializationContext ctx, Type rtType) {
        Order event = null;

        while(parser.hasNext()) {
            JsonParser.Event ev = parser.next();
            if(ev.equals(JsonParser.Event.KEY_NAME)) {
                String key = parser.getString();
                parser.next(); // move to value for 'key'
                try {
                    event = ctx.deserialize(Class.forName(key).asSubclass(Order.class), parser);
                } catch (ClassNotFoundException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }

        return event;
    }

}
