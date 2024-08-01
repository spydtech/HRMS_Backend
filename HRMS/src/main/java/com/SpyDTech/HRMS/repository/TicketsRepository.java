package com.SpyDTech.HRMS.repository;

import com.SpyDTech.HRMS.entities.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketsRepository extends JpaRepository<Tickets,Long> {
}
