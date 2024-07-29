package com.SpyDTech.HRMS.repository;

import com.SpyDTech.HRMS.entities.FacebookRecentPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaceBookRecentRepository extends JpaRepository<FacebookRecentPost,Long> {
}
