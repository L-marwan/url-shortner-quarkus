package com.maroune.urlshortner.redis;

public interface RedisService<T> {
    void set(String key, T value);

    T get(String key);

    Class<T> getObjectType();

}
