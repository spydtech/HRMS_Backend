package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.entities.ProjectDetail;
import com.SpyDTech.HRMS.repository.ProjectDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProjectDetailServiceImpl implements ProjectDetailService {
    @Autowired
    ProjectDetailRepository projectDetailRepository;
    @Override
    public ProjectDetail createProjectDetail(String projectActivity, MultipartFile file) throws IOException {
        ProjectDetail projectDetail=new ProjectDetail();
        projectDetail.setProjectActivity(projectActivity);
        projectDetail.setImage(file.getBytes());
        return projectDetailRepository.save(projectDetail);
    }
}
