package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.entities.Tickets;

import java.util.List;

public interface TicketService {
    Tickets createTickets (Tickets tickets);
    List<Tickets> getAllTickets();

public Tickets updateTicket(Long id, Tickets ticketDetails);
    String deleteTickets(Long id);
    Tickets getTicketById(Long id);
}
