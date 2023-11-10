package com.maroune.urlshortner;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class URLShortnerServiceImpl implements IURLShortnerService {

    private static final String URL_KEY_PREFIX = "url:";

    private final RedisService redisService;

    public URLShortnerServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public String getURL(String key) {
        return redisService.getStringValue(URL_KEY_PREFIX + key);
    }

    @Override
    public String shortenURL(String url) {
        var shortURL = generateKey(url);
        boolean alreadyExists = redisService.getStringValue(shortURL) != null;
        while (alreadyExists) {
            shortURL = getURL(url);
            alreadyExists = redisService.getStringValue(shortURL) != null;
        }
        redisService.setStringValue(URL_KEY_PREFIX + shortURL, url);
        return shortURL;
    }

    private String generateKey(String url) {
        return "lkkkk";
    }
}
