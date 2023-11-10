package com.maroune.urlshortner;

public class URLShortenRequest {

    private String url;

    public URLShortenRequest(){

    }

    public URLShortenRequest(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
