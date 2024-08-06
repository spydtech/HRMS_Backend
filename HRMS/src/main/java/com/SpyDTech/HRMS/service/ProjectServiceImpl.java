package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.entities.*;
import com.SpyDTech.HRMS.repository.ProjectDetailRepository;
import com.SpyDTech.HRMS.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl  implements ProjectService{
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectDetailRepository projectDetailRepository;
    @Override
    public AddProject createProject(String projectName, String clientName, LocalDate startDate, LocalDate endDate,
                                    ProjectProirity projectProirity, String rateInDollar, RateType rateType,
                                    TeamLead teamLead, Team team, MultipartFile file, String projectDescription,
                                    LeaveStatus status, String progress,Long action) throws IOException {
        AddProject addProject=new AddProject();
        addProject.setProjectName(projectName);
        addProject.setClientName(clientName);
        addProject.setStartDate(startDate);
        addProject.setEndDate(endDate);
        addProject.setProjectProirity(projectProirity);
        addProject.setRateInDollar(rateInDollar);
        addProject.setRateType(rateType);
        addProject.setTeam(team);
        addProject.setTeamLead(teamLead);
        addProject.setImage(file.getBytes());
        addProject.setProjectDescription(projectDescription);
        addProject.setStatus(status);
        addProject.setProgress(progress);
       Optional< ProjectDetail> projectDetailId=projectDetailRepository.findById(action);
       if(projectDetailId.isPresent()){
           ProjectDetail projectDetail=projectDetailId.get();
           addProject.setAction(projectDetail);
       }


        return projectRepository.save(addProject);
    }

    public List<AddProject> getAllProjects(){
        return projectRepository.findAll();
    }
}
