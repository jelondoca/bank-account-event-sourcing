package com.sergio.services.consumer.deserializers;

import com.sergio.model.WithdrawOrderPlaced;
import com.sergio.services.producers.config.JsonConfigFactory;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class JSONBOrderDeserializerTest {

    @Test
    public void shouldDeserializeContent() {
        JsonbBuilder builder = JsonbBuilder.newBuilder();
        builder.withConfig(JsonConfigFactory.config());
        Jsonb jsonb = builder.build();
        String payload = "{\"com.sergio.model.WithdrawOrderPlaced\":{\"id\":\"64c0fb74-8c37-4d7a-b174-ee6941c6c53b\",\"instant\":\"2018-05-06T12:23:50.477Z\",\"account\":\"1\",\"quantity\":2000.0}}";
        WithdrawOrderPlaced as = jsonb.fromJson(payload, WithdrawOrderPlaced.class.getGenericSuperclass());
        assertNotNull(as);
        assertEquals("1", as.getAccount());
        assertEquals("64c0fb74-8c37-4d7a-b174-ee6941c6c53b", as.getId());
        assertEquals(2000.0f, as.getQuantity());
    }

}