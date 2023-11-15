package com.maroune.urlshortner;

public record ShortenedURLData(String owner, String longURL, String ShortURL, int numberOfVisits,
                               String date) {
}
