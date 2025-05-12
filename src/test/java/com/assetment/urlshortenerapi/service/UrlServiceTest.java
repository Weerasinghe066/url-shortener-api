package com.assetment.urlshortenerapi.service;

import com.assetment.urlshortenerapi.exception.BadRequestException;
import com.assetment.urlshortenerapi.exception.UrlNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UrlServiceTest {

    @Autowired
    private UrlService urlService;

    @Test
    public void testShortenAndRetrieveUrl(){
        String longUrl = "https://www.youtube.com/";
        String shortUrl = urlService.shortenURL(longUrl);
        String retrieveLongURL = urlService.getOriginalURL(shortUrl);

        assertEquals(longUrl, retrieveLongURL);
    }

    @Test
    public void testShortenSameUrlReturnsSameShortCode() {
        String url = "https://www.youtube.com/";
        String shortenUrlOne = urlService.shortenURL(url);
        String shortenUrlTwo = urlService.shortenURL(url);

        assertEquals(shortenUrlOne, shortenUrlTwo);
    }

    @Test
    public void testInvalidUrlThrowsException() {
        assertThrows(BadRequestException.class, () -> {
            urlService.shortenURL("htp:/bad-url");
        });
    }

    @Test
    public void testNotStoredUrlThrowsException() {
        String invalidShortCode = "1111";  //as the logic shorten URL has 8 characters
        assertThrows(UrlNotFoundException.class, () -> {
            urlService.getOriginalURL(invalidShortCode);
        });
    }


}
