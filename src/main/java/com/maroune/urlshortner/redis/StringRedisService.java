package com.maroune.urlshortner.redis;

import io.quarkus.redis.datasource.RedisDataSource;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton // this was forced, need a better design
@Named("StringRedisService")
public class StringRedisService extends SimpleValuesRedisService<String> {

    public StringRedisService(RedisDataSource ds) {
        super(ds);
    }

    @Override
    public Class<String> getObjectType() {
        return String.class;
    }
}
