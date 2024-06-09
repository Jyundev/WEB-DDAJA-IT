package com.web.ddajait.service;

import java.util.List;

import com.web.ddajait.model.dto.CertificationRegistrationDto;
import com.web.ddajait.model.dto.Calendar.CalendarDto;

public interface CertificationRegistrationService {

    public List<CertificationRegistrationDto> getAllCerticationResgitration();

    public List<CalendarDto> getCalendarContent();

}