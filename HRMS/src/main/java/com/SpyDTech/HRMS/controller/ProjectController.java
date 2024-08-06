package com.SpyDTech.HRMS.controller;

import com.SpyDTech.HRMS.entities.*;
import com.SpyDTech.HRMS.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping("/AddProject")
    public ResponseEntity<AddProject> createAddProject(@RequestParam  String projectName,@RequestParam String clientName,@RequestParam LocalDate startDate
            ,@RequestParam LocalDate endDate,@RequestParam  ProjectProirity projectProirity,@RequestParam  String rateInDollar,@RequestParam  RateType rateType,
                                                       @RequestParam  TeamLead teamLead,@RequestParam  Team team,@RequestParam  MultipartFile file,@RequestParam  String projectDescription,
                                                       @RequestParam  LeaveStatus status,@RequestParam  String progress,@RequestParam Long action) throws IOException {
        return new ResponseEntity<>(projectService.createProject(projectName,  clientName,  startDate
                ,  endDate,  projectProirity,  rateInDollar,  rateType,
                 teamLead,  team,  file,  projectDescription,
                 status, progress,action), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AddProject>> getAll(){
        return new ResponseEntity<>(projectService.getAllProjects(),HttpStatus.OK);
    }

}
