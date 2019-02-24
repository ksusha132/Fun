package com.epam.service;

import com.epam.model.AuditoriumModel;

public interface AuditoriumService {
    AuditoriumModel getByName(String name);
}
