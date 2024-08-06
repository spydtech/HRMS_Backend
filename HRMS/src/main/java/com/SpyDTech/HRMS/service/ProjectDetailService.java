package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.entities.ProjectDetail;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProjectDetailService {
    ProjectDetail createProjectDetail(String projectActivity, MultipartFile file) throws IOException;
}
