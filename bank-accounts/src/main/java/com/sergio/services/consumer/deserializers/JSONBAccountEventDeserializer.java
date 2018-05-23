package com.sergio.services.consumer.deserializers;

import com.sergio.model.events.external.AccountKafkaEvent;

import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import java.lang.reflect.Type;

public class JSONBAccountEventDeserializer implements JsonbDeserializer<AccountKafkaEvent> {

    @Override
    public AccountKafkaEvent deserialize(JsonParser parser, DeserializationContext ctx, Type rtType) {
        AccountKafkaEvent event = null;

        while(parser.hasNext()) {
            JsonParser.Event ev = parser.next();
            if(ev.equals(JsonParser.Event.KEY_NAME)) {
                String key = parser.getString();
                parser.next(); // move to value for 'key'
                try {
                    event = ctx.deserialize(Class.forName(AccountKafkaEvent.class.getPackage().getName() + "." + key).asSubclass(AccountKafkaEvent.class), parser);
                } catch (ClassNotFoundException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }

        return event;
    }

}
