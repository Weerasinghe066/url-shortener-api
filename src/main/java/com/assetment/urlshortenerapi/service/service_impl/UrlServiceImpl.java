package com.assetment.urlshortenerapi.service.service_impl;

import com.assetment.urlshortenerapi.exception.BadRequestException;
import com.assetment.urlshortenerapi.exception.UrlNotFoundException;
import com.assetment.urlshortenerapi.service.UrlService;
import com.assetment.urlshortenerapi.support.InMemoryStore;
import com.assetment.urlshortenerapi.support.ShortenURLCreator;
import com.assetment.urlshortenerapi.util.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UrlServiceImpl implements UrlService {

    private static final Logger LOG = LoggerFactory.getLogger(UrlServiceImpl.class);

    private final InMemoryStore store;
    private final ShortenURLCreator shortenURLCreator;

    public UrlServiceImpl(InMemoryStore store, ShortenURLCreator shortenURLCreator) {
        this.store = store;
        this.shortenURLCreator = shortenURLCreator;
    }

    @Override
    public String shortenURL(String longUrl) throws BadRequestException {
        LOG.info("START : Store shortener URL for {}", longUrl);
        if (!UrlUtils.isValidURL(longUrl)) {
            throw new BadRequestException("Invalid URL format");
        }
        String existingCode = this.store.getShortenURLFromLongURL(longUrl);
        if (existingCode != null) {
            return existingCode;
        }
        String shortenURL = this.shortenURLCreator.generateUniqueURL(longUrl);
        this.store.storeUrl(shortenURL, longUrl);
        LOG.info("END : Store shortener URL for {}", longUrl);
        return shortenURL;
    }

    @Override
    public String getOriginalURL(String shortenUrl) throws UrlNotFoundException {
        String originalURL = this.store.getLongUrl(shortenUrl);
        if (originalURL != null) {
            return originalURL;
        } else {
            throw new UrlNotFoundException("URL not registered");
        }
    }


}
