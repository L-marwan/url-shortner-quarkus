package com.maroune.urlshortner;

public class URLShortenResponse {

    private String key;
    private String shortURL;
    private String longURL;

    public URLShortenResponse() {

    }

    public URLShortenResponse(String key, String shortURL, String longURL) {
        this.key = key;
        this.shortURL = shortURL;
        this.longURL = longURL;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    public String getLongURL() {
        return longURL;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }

}
