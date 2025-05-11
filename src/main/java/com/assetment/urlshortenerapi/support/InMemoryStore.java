package com.assetment.urlshortenerapi.support;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryStore {

    private final ConcurrentHashMap<String, String> urlStore = new ConcurrentHashMap<>();

    public void storeUrl(String shortUrl, String longUrl) {
        this.urlStore.put(shortUrl, longUrl);
    }

    public String getLongUrl(String shortenUrl) {
        return this.urlStore.get(shortenUrl);
    }

    public boolean containsShortenURL(String shortenUrl) {
        return this.urlStore.containsKey(shortenUrl);
    }

    public boolean containsLongURL(String longUrl) {
        return this.urlStore.containsValue(longUrl);
    }

    public String getShortenURLFromLongURL(String longUrl) {
        return this.urlStore
                .entrySet()
                .stream()
                .filter(entry -> longUrl.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

}
