package com.epam.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DiscountAspect {
    // count how many times each discount was given total and for specific user - total - for all and specific user - logged in user
}
