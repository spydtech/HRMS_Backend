package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.entities.TeamClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TeamClassService {
    TeamClass createTeam(String teamName,String teamLeadName, List<String> courses, List<String> employees
                        , MultipartFile file) throws IOException;
    List<TeamClass> getAllTeams();
}
