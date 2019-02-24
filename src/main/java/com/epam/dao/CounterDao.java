package com.epam.dao;

import com.epam.model.CounterModel;

public interface CounterDao {
    void save(CounterModel counter);

    void update(CounterModel counter);

    CounterModel getByNameAndType(String name, String type);
}
