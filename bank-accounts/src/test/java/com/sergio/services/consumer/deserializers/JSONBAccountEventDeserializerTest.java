package com.sergio.services.consumer.deserializers;

import com.sergio.model.events.external.AccountKafkaEvent;
import com.sergio.model.events.external.DepositOrderPlaced;
import com.sergio.services.producers.config.JsonConfigFactory;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JSONBAccountEventDeserializerTest {

    @Test
    public void deserializeDepositOrderPlaced() {
        JsonbBuilder builder = JsonbBuilder.newBuilder();
        builder.withConfig(JsonConfigFactory.config());
        Jsonb jsonb = builder.build();
        String payload = "{\"DepositOrderPlaced\":{\"id\":\"75a6e3b1-a76f-4927-904e-7f14fb283caf\",\"instant\":\"2018-05-23T21:00:41.803Z\",\"accountId\":\"fakeAccount\",\"quantity\":123.0}}";
        DepositOrderPlaced as = (DepositOrderPlaced) jsonb.fromJson(payload, AccountKafkaEvent.class);
        assertNotNull(as);
        assertEquals("fakeAccount", as.getAccountId());
        assertEquals("75a6e3b1-a76f-4927-904e-7f14fb283caf", as.getId());
        assertEquals("123.0", as.getQuantity().toString());
    }

}