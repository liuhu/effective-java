/*
 * Copyright (c) 2014-2015 Huami, Inc. All Rights Reserved.
 */

package me.liuhu.study.vertx;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map.Entry;

@Slf4j
public class AsyncRestClient {

    private static final HttpClient asyncRestClient = getRestClient(false);

    private static final HttpClient asyncSslRestClient = getRestClient(true);

    public static void requestAbs(HttpMethod method, String absoluteURI,
            Handler<HttpClientResponse> responseHandler, HttpHeaders httpHeaders, String body) {
        log.info("method {}, absoluteURI {}, httpHeaders {}, body {}", method, absoluteURI,
                httpHeaders, body);
        HttpClient client = asyncRestClient;
        if (absoluteURI.contains("https")) {
            client = asyncSslRestClient;
        }
        HttpClientRequest clientRequest = client.requestAbs(method, absoluteURI,
                responseHandler);
        if (httpHeaders != null) {
            for (Entry<String, List<String>> httpHeader : httpHeaders.entrySet()) {
                clientRequest.putHeader(httpHeader.getKey(), httpHeader.getValue());
            }
        }
        clientRequest.putHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        if (StringUtils.isNotBlank(body)) {
            clientRequest.end(body);
        } else {
            clientRequest.end();
        }
    }

    private static HttpClient getRestClient(boolean ssl) {
        Vertx vertx = Vertx.vertx();
        if (ssl) {
            return vertx.createHttpClient(new HttpClientOptions().setSsl(true));
        }
        return vertx.createHttpClient();
    }
}
