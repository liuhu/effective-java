/*
 * Copyright (c) 2014-2021 Huami, Inc. All Rights Reserved.
 */

package me.liuhu.study.effective.java.java8;

@FunctionalInterface
public interface PrinterFactory {

    PrinterFactory DEFAULT = (x) -> {
        if (x instanceof Integer) {
            return new DefaultPrinter();
        }
        return new StringPrinter();
    };

    Printer create(Object obj);
}
