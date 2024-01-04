package com.maple.mapleservice.util;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public class APIRequestInterceptor implements RequestInterceptor {
    @Value("${api-key}")
    private String apiKey;

    @Override
    public void apply(RequestTemplate template) {
        template.header("x-nxopen-api-key",apiKey);
    }

}
