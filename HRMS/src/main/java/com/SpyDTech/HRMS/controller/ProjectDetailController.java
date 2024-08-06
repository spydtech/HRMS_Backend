package com.SpyDTech.HRMS.controller;

import com.SpyDTech.HRMS.entities.ProjectDetail;
import com.SpyDTech.HRMS.service.ProjectDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/projectDetail")
public class ProjectDetailController {
    @Autowired
    ProjectDetailService projectDetailService;

    @PostMapping("/save")
    public ResponseEntity<ProjectDetail> createProjectDetail(@RequestParam String projectActivity, @RequestParam MultipartFile file) throws IOException {
        return new ResponseEntity<>(projectDetailService.createProjectDetail(projectActivity, file), HttpStatus.CREATED);
    }
}
;