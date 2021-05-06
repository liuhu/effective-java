/*
 * Copyright (c) 2014-2021 Huami, Inc. All Rights Reserved.
 */

package me.liuhu.study.effective.java.java8;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@FunctionalInterface
public interface ApplicationContextFactory {
    ApplicationContextFactory DEFAULT = (webApplicationType) -> {
        try {
            switch(webApplicationType) {
                case SERVLET:
                    return new AnnotationConfigServletWebServerApplicationContext();
                case REACTIVE:
                    return new AnnotationConfigReactiveWebServerApplicationContext();
                default:
                    return new AnnotationConfigApplicationContext();
            }
        } catch (Exception var2) {
            throw new IllegalStateException("Unable create a default ApplicationContext instance, you may need a custom ApplicationContextFactory", var2);
        }
    };

    ConfigurableApplicationContext create(WebApplicationType webApplicationType);
}
