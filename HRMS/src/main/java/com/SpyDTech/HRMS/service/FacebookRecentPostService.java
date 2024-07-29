package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.entities.FacebookRecentPost;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FacebookRecentPostService {
    public String createPost(MultipartFile file, String content) throws IOException;
    List<FacebookRecentPost> getAllPosts();
    FacebookRecentPost getByPostId(long id);
    public FacebookRecentPost updatePost(Long id,MultipartFile file,String content) throws IOException;
    String deletePost(long id);

}
