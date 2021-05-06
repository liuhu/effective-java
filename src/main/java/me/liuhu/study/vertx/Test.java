/*
 * Copyright (c) 2014-2021 Huami, Inc. All Rights Reserved.
 */

package me.liuhu.study.vertx;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Slf4j
public class Test {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);

            AsyncRestClient.requestAbs(HttpMethod.GET,
                    "http://127.0.0.1:18081/",
                    new Handler<HttpClientResponse>() {
                        @Override
                        public void handle(HttpClientResponse event) {
                            log.info("response code {}", event.statusCode());
                        }
                    },
                    requestHeaders, "" + i);
        }

    }
}
