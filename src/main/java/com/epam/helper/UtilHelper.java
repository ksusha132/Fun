package com.epam.helper;

import java.util.concurrent.atomic.AtomicLong;

public class UtilHelper {

    public static AtomicLong idCounter = new AtomicLong(1); // To do make it via AOP

    public static Long createID() {
        return idCounter.incrementAndGet();
    }

}
