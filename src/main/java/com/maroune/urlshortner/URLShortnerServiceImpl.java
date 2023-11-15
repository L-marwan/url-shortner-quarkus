package com.maroune.urlshortner;

import com.maroune.urlshortner.redis.RedisService;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Instant;

@ApplicationScoped
public class URLShortnerServiceImpl implements IURLShortnerService {

    private static final String URL_KEY_PREFIX = "url:";

    private final RedisService<ShortenedURLData> redisService;
    private final IUIDGenerator uidGenerator;
    private static final int ID_LENGTH = 6;

    public URLShortnerServiceImpl(RedisService<ShortenedURLData> redisService, IUIDGenerator uidGenerator) {
        this.redisService = redisService;
        this.uidGenerator = uidGenerator;
    }

    @Override
    public ShortenedURLData getURL(String key) {
        return redisService.get(URL_KEY_PREFIX + key);
    }

    public void incrementVisits(String key) {
        var oldURLData = redisService.get(URL_KEY_PREFIX + key);
        if (oldURLData != null) {
            var newURLData = new ShortenedURLData(oldURLData.owner(),
                    oldURLData.longURL(),
                    oldURLData.ShortURL(),
                    oldURLData.numberOfVisits() + 1,
                    oldURLData.date());
            redisService.set(URL_KEY_PREFIX + key, newURLData);
        }
    }

    @Override
    public String shortenURL(String url) {
        var shortURL = uidGenerator.generateUID(ID_LENGTH);
        boolean alreadyExists = redisService.get(shortURL) != null;
        while (alreadyExists) {// in the rare case we get a collision
            shortURL = uidGenerator.generateUID(ID_LENGTH);
            alreadyExists = redisService.get(shortURL) != null;
        }
        redisService.set(URL_KEY_PREFIX + shortURL, new ShortenedURLData("me", url, shortURL, 0, Instant.now().toString()));
        return shortURL;
    }


}
