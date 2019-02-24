package com.epam.aspect;

import com.epam.dao.CounterDao;
import com.epam.model.CounterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AopHelper {

    private static CounterDao counterDao;

    @Autowired
    public AopHelper(CounterDao counterDao) {
        AopHelper.counterDao = counterDao;
    }

    public static void checkAndSet(String eventName, String type) {
        CounterModel counter = counterDao.getByNameAndType(eventName, type);
        if (counter != null) {
            incrementAndSetCounter(counter);
        } else {
            createCounter(eventName, type);
        }
    }

    static void incrementAndSetCounter(CounterModel counter) {
        counter.setCount(counter.getCount() + 1); // todo cuncurrency add
        counterDao.update(counter);
    }

    static void createCounter(String s, String countType) {
        CounterModel cModel = new CounterModel();
        cModel.setName(s);
        cModel.setCount(1);
        cModel.setCountType(countType);
        counterDao.save(cModel);
    }
}
