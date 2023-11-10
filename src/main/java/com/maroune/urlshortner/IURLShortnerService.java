package com.maroune.urlshortner;

public interface IURLShortnerService {
    public String getURL(String key);

    public String shortenURL(String url);
}
