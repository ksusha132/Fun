package com.epam.helper;

import java.util.concurrent.atomic.AtomicLong;

public class UserHelper {

    public static AtomicLong idCounter = new AtomicLong(1);

    public static Long createID() {
        return idCounter.incrementAndGet();
    }

}
