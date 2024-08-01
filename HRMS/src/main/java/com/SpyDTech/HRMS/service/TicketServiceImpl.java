package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.entities.Tickets;
import com.SpyDTech.HRMS.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements  TicketService{

    @Autowired
    TicketsRepository ticketsRepository;

    @Override
    public Tickets createTickets(Tickets tickets) {
        return ticketsRepository.save(tickets);
    }

    @Override
    public List<Tickets> getAllTickets() {
        return ticketsRepository.findAll();
    }


  @Override
    public Tickets updateTicket(Long id, Tickets ticketDetails) {
        Optional<Tickets> optionalTicket = ticketsRepository.findById(id);

        if (optionalTicket.isPresent()) {
            Tickets existingTicket = optionalTicket.get();
            existingTicket.setAssaignBy(ticketDetails.getAssaignBy());
            existingTicket.setAssaignTo(ticketDetails.getAssaignBy());
            existingTicket.setEmail(ticketDetails.getEmail());
            existingTicket.setSubject(ticketDetails.getSubject());
            existingTicket.setStatus(ticketDetails.getStatus());
            existingTicket.setDate(ticketDetails.getDate());
            return ticketsRepository.save(existingTicket);
        } else {
            throw new RuntimeException("Ticket not found with id " + id);
        }
    }

    @Override
    public String deleteTickets(Long id) {
        Optional<Tickets> ticketsOptional=ticketsRepository.findById(id);
        if(ticketsOptional.isPresent()){
            Tickets tickets1=ticketsOptional.get();
            ticketsRepository.deleteById(id);
            return "deleted successfully";
        }
        return "id not found";
    }

    @Override
    public Tickets getTicketById(Long id) {
        Optional<Tickets> ticketsOptional=ticketsRepository.findById(id);
        if(ticketsOptional.isPresent()){
            Tickets tickets1=ticketsOptional.get();
            return tickets1;

        }else{
            throw new RuntimeException("id not found");
        }
    }
}
