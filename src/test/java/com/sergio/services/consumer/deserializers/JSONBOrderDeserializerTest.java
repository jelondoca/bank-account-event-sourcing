package com.sergio.services.consumer.deserializers;

import com.sergio.model.orders.WithdrawOrderPlaced;
import com.sergio.model.orders.WithdrawOrderRejected;
import com.sergio.services.producers.config.JsonConfigFactory;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class JSONBOrderDeserializerTest {

    @Test
    public void shouldDeserializeWithdrawOrderPlacedContent() {
        JsonbBuilder builder = JsonbBuilder.newBuilder();
        builder.withConfig(JsonConfigFactory.config());
        Jsonb jsonb = builder.build();
        String payload = "{\"com.sergio.model.orders.WithdrawOrderPlaced\":{\"id\":\"64c0fb74-8c37-4d7a-b174-ee6941c6c53b\",\"instant\":\"2018-05-06T12:23:50.477Z\",\"account\":\"1\",\"quantity\":2000.0}}";
        WithdrawOrderPlaced as = jsonb.fromJson(payload, WithdrawOrderPlaced.class.getGenericSuperclass());
        assertNotNull(as);
        assertEquals("1", as.getAccount());
        assertEquals("64c0fb74-8c37-4d7a-b174-ee6941c6c53b", as.getId());
        assertEquals(2000.0f, as.getQuantity());
    }

    @Test
    public void shouldDeserializeWithdrawOrderRejectedContent() {
        JsonbBuilder builder = JsonbBuilder.newBuilder();
        builder.withConfig(JsonConfigFactory.config());
        Jsonb jsonb = builder.build();
        String payload = "{\"com.sergio.model.orders.WithdrawOrderRejected\":{\"id\":\"f6bdcfc4-a8dc-4d1f-9b70-9560633c3b3d\",\"instant\":\"2018-05-06T13:17:03.668Z\",\"reason\":\"account not found\"}}";
        WithdrawOrderRejected as = jsonb.fromJson(payload, WithdrawOrderRejected.class.getGenericSuperclass());
        assertNotNull(as);
    }

}