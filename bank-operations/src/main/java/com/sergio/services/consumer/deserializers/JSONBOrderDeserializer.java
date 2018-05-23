package com.sergio.services.consumer.deserializers;

import com.sergio.model.events.Order;

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
                    event = ctx.deserialize(Class.forName(Order.class.getPackage().getName() + "." + key)
                            .asSubclass(Order.class), parser);
                } catch (ClassNotFoundException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }

        return event;
    }

}
