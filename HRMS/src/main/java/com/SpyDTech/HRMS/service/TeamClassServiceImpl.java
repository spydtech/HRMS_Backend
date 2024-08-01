package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.entities.TeamClass;
import com.SpyDTech.HRMS.repository.TeamClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class TeamClassServiceImpl implements  TeamClassService{
    @Autowired
    TeamClassRepository teamClassRepository;
    @Override
    public TeamClass createTeam(String teamName, String teamLeadName,List<String> courses, List<String> employees,
                                 MultipartFile file) throws IOException {
        TeamClass teamClass=new TeamClass();
        teamClass.setTeamName(teamName);
        teamClass.setTeamLeadName(teamLeadName);
        teamClass.setCourses(courses);
        teamClass.setEmployees(employees);
        teamClass.setTeamLeadCount(1);
        teamClass.setEmployeesCount(employees.size());
        teamClass.setImage(file.getBytes());
        return teamClassRepository.save(teamClass);
    }

    @Override
    public List<TeamClass> getAllTeams() {
        return teamClassRepository.findAll();
    }
}
