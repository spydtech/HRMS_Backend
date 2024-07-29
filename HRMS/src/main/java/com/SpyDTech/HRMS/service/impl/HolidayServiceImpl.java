package com.SpyDTech.HRMS.service.impl;

import com.SpyDTech.HRMS.dto.ErrorResponse;
import com.SpyDTech.HRMS.dto.HolidayDto;
import com.SpyDTech.HRMS.dto.HolidayRequest;
import com.SpyDTech.HRMS.entities.HolidaysList;
import com.SpyDTech.HRMS.repository.HolidayRepository;
import com.SpyDTech.HRMS.service.HolidayService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class HolidayServiceImpl implements HolidayService {
    @Autowired
    HolidayRepository holidayRepository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    @Override
    public ResponseEntity addHoliday(HolidayRequest holidayRequest) {
        if (Boolean.FALSE.equals(isValid(holidayRequest.getHolidayName()))) {
            ErrorResponse errorResponse = new ErrorResponse("Holiday name can't be null/empty..");
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

        if (Boolean.FALSE.equals(isValid(holidayRequest.getDate()))) {
            ErrorResponse errorResponse = new ErrorResponse("date can't be null/empty..");
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        if (Boolean.FALSE.equals(isValidLocalDate(holidayRequest.getDay()))) {
            ErrorResponse errorResponse = new ErrorResponse("day can't be null/empty..");
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

        HolidaysList holidaysList = setDetails(holidayRequest);
        if(!holidayRepository.existsByHolidayName(holidayRequest.getHolidayName())) {
            holidayRepository.save(holidaysList);
        }else{
            ErrorResponse errorResponse = new ErrorResponse("Holiday with the same name already exists.");
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        return ResponseEntity.ok(holidaysList);
    }


    public Boolean isValid(String value){
        if((value!=null) && (!value.trim().isEmpty())){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    public Boolean isValidLocalDate(LocalDate value){
        if((value!=null)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public ResponseEntity updateHoliday(String holidayName,HolidayRequest holidayRequest) {
        Optional<HolidaysList> optionalHolidaysList=holidayRepository.findByHolidayName(holidayName);
        HolidaysList holidaysList=new HolidaysList();
        if(optionalHolidaysList.isPresent()){
             holidaysList=optionalHolidaysList.get();
             if(holidayRequest.getDay()!=null) {
                 holidaysList.setDay(holidayRequest.getDay());
             }
             if(holidayRequest.getDate()!=null){
            holidaysList.setDate(holidayRequest.getDate());
             }
             if(holidayRequest.getHolidayName()!=null) {
                 holidaysList.setHolidayName(holidayRequest.getHolidayName());
             }
            holidayRepository.save(holidaysList);
        }else{
            ErrorResponse errorResponse = new ErrorResponse("Holiday is not found.");
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        return ResponseEntity.ok(holidaysList);
    }

    @Override
    @Transactional
    public boolean deleteHoliday(String holidayName,LocalDate localDate ) {
        if (holidayName != null && holidayRepository.existsByHolidayName(holidayName)) {
            holidayRepository.deleteByHolidayName(holidayName);
            return true;
        }
        if (localDate != null && holidayRepository.existsByDay(localDate)) {
            holidayRepository.deleteByDay(localDate);
            return true;
        }
        return false;
    }

    @Override
    public ResponseEntity getHolidaysList() {
        List<HolidaysList> holidays=holidayRepository.findAll(Sort.by(Sort.Order.asc("day")));
        List<HolidayDto> holidayDto = holidays.stream()
                .map(this::getDetails)
                .sorted((dto1, dto2) -> {
                    LocalDate date1 = LocalDate.parse(dto1.getDay(), FORMATTER);
                    LocalDate date2 = LocalDate.parse(dto2.getDay(), FORMATTER);
                    return date1.compareTo(date2);
                })
                .collect(Collectors.toList());
       return  ResponseEntity.ok(holidayDto);
    }

    public HolidayDto getDetails(HolidaysList holidayRequest){
        HolidayDto holidaysList=new HolidayDto();
        holidaysList.setId(holidayRequest.getId());
        holidaysList.setDay(getFormattedDay(holidayRequest.getDay()));
        holidaysList.setDate(holidayRequest.getDate());
        holidaysList.setHolidayName(holidayRequest.getHolidayName());
        return holidaysList;
    }

    private String getFormattedDay(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return date.format(formatter);
    }



    public HolidaysList setDetails(HolidayRequest holidayRequest){
        HolidaysList holidaysList=new HolidaysList();
        holidaysList.setDay(holidayRequest.getDay());
        holidaysList.setDate(holidayRequest.getDate());
        holidaysList.setHolidayName(holidayRequest.getHolidayName());
      return holidaysList;
    }
}
