package com.SpyDTech.HRMS.controller;

import com.SpyDTech.HRMS.dto.AddEmployeeRequest;
import com.SpyDTech.HRMS.dto.HolidayRequest;
import com.SpyDTech.HRMS.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
public class HolidayController {
    @Autowired
    HolidayService holidayService;

    @PostMapping("/create/holiday")
    public ResponseEntity saveHoliday(@RequestBody HolidayRequest holidayRequest){
        return ResponseEntity.ok(holidayService.addHoliday(holidayRequest));
    }

    @PutMapping("/edit/holiday/{holidayName}")
    public ResponseEntity updateHoliday(@PathVariable String holidayName,@RequestBody HolidayRequest holidayRequest){
        return ResponseEntity.ok(holidayService.updateHoliday(holidayName,holidayRequest));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteHoliday(@RequestParam(required = false) String holidayName,
                                                @RequestParam(required = false) LocalDate date){
        boolean isRemoved = holidayService.deleteHoliday(holidayName, date);
        if (isRemoved) {
            return new ResponseEntity<>("Holiday deleted successfully",  HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Holiday not found", HttpStatus.NOT_FOUND);
        }
    }
@GetMapping("/get_all")
    public ResponseEntity getAllHolidays(){
        return ResponseEntity.ok(holidayService.getHolidaysList());
    }


}
