package com.maroune.urlshortner;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RedisService {
    private final ValueCommands<String, String> stringCommands;
    private final ValueCommands<String, Integer> intCommands;

    public RedisService(RedisDataSource ds) {
        stringCommands = ds.value(String.class);
        intCommands = ds.value(Integer.class);
    }

    String getStringValue(String key) {
        return stringCommands.get(key);
    }

    Integer getIntegerValue(String key) {
        Integer val = intCommands.get(key);
        return val == null ? 0 : val;
    }

    void setStringValue(String key, String value) {
        stringCommands.set(key, value);
    }

    void setIntegerValue(String key, Integer value) {
        intCommands.set(key, value);
    }
}
