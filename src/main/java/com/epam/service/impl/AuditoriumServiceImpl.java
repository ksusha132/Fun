package com.epam.service.impl;

import com.epam.auditorium.AuditBlue;
import com.epam.auditorium.AuditGreen;
import com.epam.auditorium.AuditRed;
import com.epam.model.AuditoriumModel;
import com.epam.service.AuditoriumService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    private AuditBlue auditBlue;

    @Autowired
    private AuditGreen auditGreen;

    @Autowired
    private AuditRed auditRed;

    @Override
    public AuditoriumModel getByName(String name) {
        AuditoriumModel auditoriumModel = new AuditoriumModel();
        switch (name) {
            case ("greenStage"):
                BeanUtils.copyProperties(auditGreen, auditoriumModel);
                break;
            case ("blueStage"):
                BeanUtils.copyProperties(auditBlue, auditoriumModel);
                break;
            case ("redStage"):
                BeanUtils.copyProperties(auditRed, auditoriumModel);
                break;
        }
        return auditoriumModel;
    }
}
