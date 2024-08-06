package com.SpyDTech.HRMS.repository;

import com.SpyDTech.HRMS.entities.HolidaysList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface HolidayRepository extends JpaRepository<HolidaysList,Long> {
    Optional<HolidaysList> findByHolidayName(String holidayName);
    void deleteById(Long id);
    void deleteByDay(LocalDate localDate);

    boolean existsByHolidayName(String holidayName);
    boolean existsByDay(LocalDate localDate);


    void deleteByHolidayName(String holidayName);
}
