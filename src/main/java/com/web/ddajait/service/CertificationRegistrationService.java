package com.web.ddajait.service;

import java.util.List;

import com.web.ddajait.model.dto.Calendar.CalendarDto;
import com.web.ddajait.model.dto.CertificateRegister.CertificationRegistrationDto;

public interface CertificationRegistrationService {

    public List<CertificationRegistrationDto> getAllCerticationResgitration();

    public List<CalendarDto> getCalendarContent();

}