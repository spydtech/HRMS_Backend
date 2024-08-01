package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.entities.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface ProjectService {
    AddProject createProject(String projectName, String clientName, LocalDate startDate
    , LocalDate endDate, ProjectProirity projectProirity, String rateInDollar, RateType rateType,
                             TeamLead teamLead, Team team, MultipartFile file, String projectDescription,
                             LeaveStatus status,String progress,Long action) throws IOException;
    public List<AddProject> getAllProjects();
}
