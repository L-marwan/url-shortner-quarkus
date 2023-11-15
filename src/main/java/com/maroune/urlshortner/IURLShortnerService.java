package com.maroune.urlshortner;

public interface IURLShortnerService {
    ShortenedURLData getURL(String key);

    void incrementVisits(String key);

    String shortenURL(String url);
}
