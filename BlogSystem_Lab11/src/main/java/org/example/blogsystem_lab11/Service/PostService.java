package org.example.blogsystem_lab11.Service;

import lombok.RequiredArgsConstructor;
import org.example.blogsystem_lab11.Api.ApiException;
import org.example.blogsystem_lab11.Model.Post;
import org.example.blogsystem_lab11.Repository.CategoryRepository;
import org.example.blogsystem_lab11.Repository.PostRepository;
import org.example.blogsystem_lab11.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<Post> getAll() { return postRepository.findAll(); }

    public void addPost(Post post) {
        if (userRepository.giveMeUserById(post.getUserId()) == null) throw new ApiException("User ID not found");
        if (categoryRepository.giveMeCategoryById(post.getCategoryId()) == null) throw new ApiException("Category ID not found");
        postRepository.save(post);
    }

    public void updatePost(Integer id, Post post) {
        Post old = postRepository.giveMePostById(id);
        if (old == null) throw new ApiException("Post not found");
        if (userRepository.giveMeUserById(post.getUserId()) == null) throw new ApiException("User ID not found");
        if (categoryRepository.giveMeCategoryById(post.getCategoryId()) == null) throw new ApiException("Category ID not found");

        old.setTitle(post.getTitle());
        old.setContent(post.getContent());
        old.setCategoryId(post.getCategoryId());
        old.setUserId(post.getUserId());
        postRepository.save(old);
    }

    public void deletePost(Integer id) {
        Post post = postRepository.giveMePostById(id);
        if (post == null) throw new ApiException("Post not found");
        postRepository.delete(post);
    }

    //Extra
    public List<Post> getPostsByUserId(Integer userId) {
        if (userRepository.giveMeUserById(userId) == null) throw new ApiException("User not found");
        List<Post> posts = postRepository.giveMeAllByUserId(userId);
        if (posts.isEmpty())
            throw new ApiException("No posts found for this user");
        return posts;
    }

    public List<Post> getPostsByCategoryId(Integer categoryId) {
        if (categoryRepository.giveMeCategoryById(categoryId) == null)
            throw new ApiException("Category not found");
        List<Post> posts = postRepository.givMeAllByCategoryId(categoryId);
        if (posts.isEmpty())
            throw new ApiException("No posts found for this category");
        return posts;
    }

    public Post getPostByTitle(String title) {
        Post post = postRepository.giveMePostByTitle(title);
        if (post == null)
            throw new ApiException("Post with this title not found");
        return post;
    }

    public List<Post> getPostsBeforeDate(LocalDate date) {
        List<Post> posts = postRepository.giveMeAllPostsBeforeDate(date);
        if (posts.isEmpty())
            throw new ApiException("No posts found before this date");
        return posts;
    }

    public List<Post> searchPosts(String keyword) {
        List<Post> posts = postRepository.giveMeByContent(keyword);
        if (posts.isEmpty())
            throw new ApiException("No posts match your search");
        return posts;
    }
    public List<Post> getLatestPosts() {
        List<Post> posts = postRepository.giveMeAllOrderByPublishDateDesc();
        if (posts.isEmpty()) {
            throw new ApiException("No posts found");
        }
        return posts.stream().limit(5).toList();
    }



}
