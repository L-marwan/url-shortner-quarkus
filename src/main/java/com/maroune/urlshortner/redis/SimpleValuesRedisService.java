package com.maroune.urlshortner.redis;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;

public abstract class SimpleValuesRedisService<T> implements RedisService<T> {

    private final ValueCommands<String, T> valueCommands;

    SimpleValuesRedisService(RedisDataSource ds) {
        valueCommands = ds.value(getObjectType());
    }

    @Override
    public void set(String key, T value) {
        valueCommands.set(key, value);
    }

    @Override
    public T get(String key) {
        return valueCommands.get(key);
    }

}
