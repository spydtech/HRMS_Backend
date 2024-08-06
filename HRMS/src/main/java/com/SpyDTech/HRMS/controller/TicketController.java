package com.SpyDTech.HRMS.controller;

import com.SpyDTech.HRMS.entities.Tickets;
import com.SpyDTech.HRMS.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/save")
    public ResponseEntity<Tickets> createTickets(@RequestBody Tickets tickets){
        return new ResponseEntity<>(ticketService.createTickets(tickets), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Tickets>> getAll(){
        return new ResponseEntity<>(ticketService.getAllTickets(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tickets> updatingTickets(@PathVariable long id,@RequestBody Tickets tickets){
        return new ResponseEntity<>(ticketService.updateTicket(id,tickets),HttpStatus.ACCEPTED);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletingTicket(@PathVariable long id){
        return new ResponseEntity<>(ticketService.deleteTickets(id),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tickets> getTicketById(@PathVariable Long id){
        return new ResponseEntity<>(ticketService.getTicketById(id),HttpStatus.OK);
    }

}
