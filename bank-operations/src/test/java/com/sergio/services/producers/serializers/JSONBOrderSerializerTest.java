package com.sergio.services.producers.serializers;

import com.sergio.model.events.DepositOrderPlaced;
import com.sergio.services.producers.config.JsonConfigFactory;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

public class JSONBOrderSerializerTest {

    @Test
    public void serialize() {
        JsonbBuilder builder = JsonbBuilder.newBuilder();
        builder.withConfig(JsonConfigFactory.config());
        Jsonb jsonb = builder.build();
        DepositOrderPlaced orderPlaced = new DepositOrderPlaced("fakeAccount", 123f);
        String payload = jsonb.toJson(orderPlaced, DepositOrderPlaced.class.getGenericSuperclass());
        System.out.println(payload);
    }

}