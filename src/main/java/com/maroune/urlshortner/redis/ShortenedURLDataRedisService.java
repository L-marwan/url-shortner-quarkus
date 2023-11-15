package com.maroune.urlshortner.redis;

import com.maroune.urlshortner.ShortenedURLData;
import io.quarkus.redis.datasource.RedisDataSource;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton()
@Named("ShortenedURLDataRedisService")
public class ShortenedURLDataRedisService extends SimpleValuesRedisService<ShortenedURLData> {
    ShortenedURLDataRedisService(RedisDataSource ds) {
        super(ds);
    }

    @Override
    public Class<ShortenedURLData> getObjectType() {
        return ShortenedURLData.class;
    }
}
