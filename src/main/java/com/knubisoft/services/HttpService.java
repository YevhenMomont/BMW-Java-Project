package com.knubisoft.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpService {

    public <T> ResponseEntity<T> performGetRequest(Class<T> responseClass, String URI) {
        return new RestTemplate().getForEntity(URI, responseClass);
    }
}
