package org.example.blogsystem_lab11.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.blogsystem_lab11.Api.ApiResponse;
import org.example.blogsystem_lab11.Model.Post;
import org.example.blogsystem_lab11.Service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body(postService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        postService.addPost(post);
        return ResponseEntity.status(201).body(new ApiResponse("Post added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Integer id, @RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        postService.updatePost(id, post);
        return ResponseEntity.status(200).body(new ApiResponse("Post updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse("Post deleted successfully"));
    }

    //Extra

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<?> getByUser(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(postService.getPostsByUserId(userId));
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<?> getByCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.status(200).body(postService.getPostsByCategoryId(categoryId));
    }

    @GetMapping("/by-title/{title}")
    public ResponseEntity<?> getByTitle(@PathVariable String title) {
        return ResponseEntity.status(200).body(postService.getPostByTitle(title));
    }

    @GetMapping("/before-date/{date}")
    public ResponseEntity<?> getBeforeDate(@PathVariable LocalDate date) {
        return ResponseEntity.status(200).body(postService.getPostsBeforeDate(date));
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> search(@PathVariable String keyword) {
        return ResponseEntity.status(200).body(postService.searchPosts(keyword));
    }



}
