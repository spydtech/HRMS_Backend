package com.SpyDTech.HRMS.controller;

import com.SpyDTech.HRMS.entities.FacebookRecentPost;
import com.SpyDTech.HRMS.repository.FaceBookRecentRepository;
import com.SpyDTech.HRMS.service.FacebookRecentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/facebookRecentPost")
public class FacebookRecentPostController {
    @Autowired
    FacebookRecentPostService facebookRecentPostService;

    @PostMapping("/create")
    public ResponseEntity<String> createPost(@RequestParam String content, @RequestParam MultipartFile file) throws IOException {
        return new ResponseEntity<>(facebookRecentPostService.createPost(file,content), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacebookRecentPost> getPostById(@PathVariable long id){
        return new ResponseEntity<>(facebookRecentPostService.getByPostId(id),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FacebookRecentPost>> getAllPost(){
        return new ResponseEntity<>(facebookRecentPostService.getAllPosts(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacebookRecentPost> updatePost(@PathVariable long id,  @RequestParam MultipartFile file,String content) throws IOException {
        return new ResponseEntity<>(facebookRecentPostService.updatePost(id,file,content),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        return new ResponseEntity<>(facebookRecentPostService.deletePost(id),HttpStatus.OK);
    }
}
