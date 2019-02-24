package com.epam.aspect;

import com.epam.dao.CounterDao;
import com.epam.dto.EventDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.epam.aspect.AopHelper.checkAndSet;

@Aspect
@Component
public class DiscountAspect {
    // count how many times each discount was given total and for specific user - total - for all and specific user - logged in user

    private final CounterDao counterDao;

    @Autowired
    public DiscountAspect(CounterDao counterDao) {
        this.counterDao = counterDao;
    }

    @Pointcut("execution(public * com.epam.service.DiscountService.getDiscount(..))")
    private void getDiscount() {
    }

    @Around("getDiscount()")
    public Integer countBeforeGetByName(ProceedingJoinPoint joinPoint) throws Throwable {
        String discountType = getDiscount(joinPoint);
        EventDto eventDto = (EventDto) joinPoint.getArgs()[1];
        String eventName = eventDto.getName();

        checkAndSet(eventName, discountType);
        return (Integer) joinPoint.proceed();
    }

    private String getDiscount(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer discount = (Integer) joinPoint.proceed();
        if (discount == 70) {
            return "NEYYEAR";
        } else if (discount == 7) {
            return "BIRTHDAY";
        } else if (discount == 5) {
            return "TENTICKET";
        }
        return null;
    }

}
