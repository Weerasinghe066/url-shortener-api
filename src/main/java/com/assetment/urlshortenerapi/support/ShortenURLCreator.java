package com.assetment.urlshortenerapi.support;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class ShortenURLCreator {

    private final InMemoryStore store;

    public ShortenURLCreator(InMemoryStore inMemoryStore) {
        this.store = inMemoryStore;
    }


    /**
     * Generates a unique shortened URL key by hashing the input URL along with a suffix.
     * Only the first 8 characters of the hash are used to keep the short URL compact.
     * <p>
     * To reduce the chance of hash collisions (since only 8 characters are used),
     * a numeric suffix is appended to the original URL before hashing.
     * If a generated short URL already exists in the store, the suffix is incremented
     * and a new hash is generated until a unique one is found.
     *
     * @param longURL The original long URL to shorten.
     * @return A unique 8-character short URL key.
     */
    public String generateUniqueURL(String longURL) {
        int suffix = 0;
        String shortenURL;
        do {
            String input = LocalDateTime.now() + longURL + suffix;
            shortenURL = this.generateShortHash(input);
            suffix++;
        } while (this.store.containsShortenURL(shortenURL));

        return shortenURL;
    }

    private String generateShortHash(String input) {
        return Hashing.murmur3_32_fixed()
                .hashString(input, StandardCharsets.UTF_8)
                .toString()
                .substring(0, 8);
    }


}
