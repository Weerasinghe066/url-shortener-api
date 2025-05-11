package com.assetment.urlshortenerapi.util;

import java.net.URL;

public final class UrlUtils {

    private UrlUtils() {
    }

    public static boolean isValidURL(String url){
        try{
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
