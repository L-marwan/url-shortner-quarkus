package com.maroune.urlshortner;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class URLShortnerServiceImpl implements IURLShortnerService {

    private static final String URL_KEY_PREFIX = "url:";

    private final RedisService redisService;
    private final IUIDGenerator uidGenerator;
    private static final int ID_LENGTH = 6;

    public URLShortnerServiceImpl(RedisService redisService, IUIDGenerator uidGenerator) {
        this.redisService = redisService;
        this.uidGenerator = uidGenerator;
    }

    @Override
    public String getURL(String key) {
        return redisService.getStringValue(URL_KEY_PREFIX + key);
    }

    @Override
    public String shortenURL(String url) {
        var shortURL = uidGenerator.generateUID(ID_LENGTH);
        boolean alreadyExists = redisService.getStringValue(shortURL) != null;
        while (alreadyExists) {// in the rare case we get a collision
            shortURL = uidGenerator.generateUID(ID_LENGTH);
            alreadyExists = redisService.getStringValue(shortURL) != null;
        }
        redisService.setStringValue(URL_KEY_PREFIX + shortURL, url);
        return shortURL;
    }


}
