package com.assetment.urlshortenerapi.service;

import org.springframework.stereotype.Service;

@Service
public interface UrlService {

    String shortenURL(String longURl);

    String getOriginalURL(String shortenURL);

}
