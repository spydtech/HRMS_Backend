package com.SpyDTech.HRMS.controller;

import com.SpyDTech.HRMS.entities.TeamClass;
import com.SpyDTech.HRMS.service.TeamClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Team")
public class TeamClassController {
    @Autowired
    TeamClassService teamClassService;

    @PostMapping("/save")
    public ResponseEntity<TeamClass> createTeam(@RequestParam String teamName, @RequestParam String teamLeadName,@RequestParam List<String> courses, @RequestParam List<String> employees,
                                              @RequestParam MultipartFile file) throws IOException {
        return new ResponseEntity<>(teamClassService.createTeam( teamName,teamLeadName,  courses, employees
        ,  file), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public  ResponseEntity<List<TeamClass>> getAllTeams(){
        return new ResponseEntity<>(teamClassService.getAllTeams(),HttpStatus.OK);
    }
}
