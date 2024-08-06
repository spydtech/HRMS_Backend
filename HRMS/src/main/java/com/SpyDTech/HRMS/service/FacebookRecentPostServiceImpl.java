package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.entities.FacebookRecentPost;
import com.SpyDTech.HRMS.repository.FaceBookRecentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FacebookRecentPostServiceImpl  implements FacebookRecentPostService{

    @Autowired
    FaceBookRecentRepository faceBookRecentRepository;

    @Override
    public String createPost(MultipartFile file,String content) throws IOException {
        FacebookRecentPost facebookRecentPost=new FacebookRecentPost();
        facebookRecentPost.setContent(content);
        facebookRecentPost.setImage(file.getBytes());
        faceBookRecentRepository.save(facebookRecentPost);
        return "posted successfully";
    }

    @Override
    public List<FacebookRecentPost> getAllPosts() {
        return faceBookRecentRepository.findAll();
    }

    @Override
    public FacebookRecentPost getByPostId(long id) {
        Optional<FacebookRecentPost> optionalId=faceBookRecentRepository.findById(id);
        if (optionalId.isPresent()){
           FacebookRecentPost facebookRecentPost= optionalId.get();
           return facebookRecentPost;
        }
        throw new RuntimeException("id not found");
    }

    @Override
    public FacebookRecentPost updatePost(Long id,MultipartFile file,String content) throws IOException {
        Optional<FacebookRecentPost> optionalId=faceBookRecentRepository.findById(id);
        if (optionalId.isPresent()){
            FacebookRecentPost facebookRecentPost= optionalId.get();
            facebookRecentPost.setContent(content);
            facebookRecentPost.setImage(file.getBytes());
            return facebookRecentPost;
        }
        throw new RuntimeException("id not found");
    }

    @Override
    public String deletePost(long id) {
        Optional<FacebookRecentPost> optionalId=faceBookRecentRepository.findById(id);
        if (optionalId.isPresent()){
            FacebookRecentPost facebookRecentPost= optionalId.get();
             faceBookRecentRepository.deleteById(id);
            return "deleted success";
        }
        return "not deleted";
    }
}
