package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.dto.HolidayDto;
import com.SpyDTech.HRMS.dto.HolidayRequest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface HolidayService {
    ResponseEntity addHoliday(HolidayRequest holidayRequest);

    ResponseEntity updateHoliday(String holidayName,HolidayRequest holidayRequest);

    boolean deleteHoliday(String holidayId, LocalDate date);
    ResponseEntity<List<HolidayDto>> getHolidaysList();
}
