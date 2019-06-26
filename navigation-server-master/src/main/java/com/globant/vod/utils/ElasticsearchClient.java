/* (c) Disney. All rights reserved. */
package com.globant.vod.utils;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("ElasticClient")
public class ElasticsearchClient {

    @Autowired
    private final RestTemplate rest;
    private final String url;

    @Autowired
    public ElasticsearchClient(
            RestTemplate rest,
            @Value("${elasticsearch.host}") String url,
            @Value("${elasticsearch.port}") String port) {
        this.rest = rest;
        this.url = url + ":" + port + "/";
    }

    public <T> T get(String path, Class<T> clazz) {

        return this.get(path, null, clazz);
    }

    public <T> T get(String path, Map<String, Object> headers, Class<T> clazz) {

        final HttpEntity<T> entity = this.getEntity(null, headers);
        ResponseEntity<T> response = this.rest.exchange(this.url + path, HttpMethod.GET, entity, clazz);
        return response.getBody();
    }

    public <T> T post(String path, String body, Class<T> clazz) {

        return this.post(path, body, null, clazz);
    }

    public <T> T post(String path, String body, Map<String, Object> headers, Class<T> clazz) {

        return this.call(HttpMethod.POST, path, body, headers, clazz);
    }

    public <T> T put(String path, String body, Class<T> clazz) {

        return this.put(path, body, null, clazz);
    }

    public <T> T put(String path, String body, Map<String, Object> headers, Class<T> clazz) {

        return this.call(HttpMethod.PUT, path, body, headers, clazz);
    }

    public <T> T delete(String path, Class<T> clazz) {
        return this.delete(path, Collections.emptyMap(), clazz);
    }

    public <T> T delete(String path, Map<String, Object> headers, Class<T> clazz) {

        return this.call(HttpMethod.DELETE, path, null, headers, clazz);
    }

    private <T> T call(HttpMethod method, String path, String body, Map<String, Object> headers, Class<T> clazz) {
        final String urlToCall = this.url + path;
        final HttpEntity<String> entity = this.getEntity(body, headers);
        ResponseEntity<T> response = this.rest.exchange(urlToCall, method, entity, clazz);
        return response.getBody();
    }

    private <T> HttpEntity<T> getEntity(T body, Map<String, Object> headers) {

        final HttpHeaders restHeaders = new HttpHeaders();

        if (null != headers) {
            headers.entrySet().stream().forEach(h -> restHeaders.add(h.getKey(), h.getValue().toString()));
        }

        return new HttpEntity<T>(body, restHeaders);
    }

}
