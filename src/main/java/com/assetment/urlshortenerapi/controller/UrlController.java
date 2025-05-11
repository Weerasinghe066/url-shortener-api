package com.assetment.urlshortenerapi.controller;

import com.assetment.urlshortenerapi.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController
public class UrlController {

    private static final Logger LOG = LoggerFactory.getLogger(UrlController.class);

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @RequestMapping(value = "/shortenerUrl", headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<String> shortenerUrl(@RequestBody String longUrl, HttpServletRequest request) {
        LOG.info("START : Creating shortener URL for {}", longUrl);
        String shortenedURL =  ServletUriComponentsBuilder
                .fromRequestUri(request)
                .replacePath(urlService.shortenURL(longUrl))
                .build()
                .toUriString();
        LOG.info("END : Creating shortener URL for {}", longUrl);

        return new ResponseEntity<>(shortenedURL, HttpStatus.OK);
    }


    @RequestMapping(value = "/{shortenUrl}", headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortenUrl, HttpServletResponse response) throws IOException {
        LOG.info("START : Redirecting to original URL from {}", shortenUrl);
        String originalURL = urlService.getOriginalURL(shortenUrl);
        LOG.info("END : Redirecting to original URL from {}", shortenUrl);

        response.sendRedirect(originalURL);
        return null;
    }

    @RequestMapping(value = "/getOriginalUrl", headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<?> getOriginalUrl(@RequestBody String shortenURL) {
        LOG.info("START : Get original URL for {}", shortenURL);
        String originalURL = urlService.getOriginalURL(shortenURL);
        LOG.info("END : Get original URL for {}", shortenURL);

        return new ResponseEntity<>(originalURL, HttpStatus.OK);
    }

}
