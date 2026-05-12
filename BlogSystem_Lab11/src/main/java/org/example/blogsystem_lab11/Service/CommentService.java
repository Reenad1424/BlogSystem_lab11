package org.example.blogsystem_lab11.Service;

import lombok.RequiredArgsConstructor;
import org.example.blogsystem_lab11.Api.ApiException;
import org.example.blogsystem_lab11.Model.Comment;
import org.example.blogsystem_lab11.Repository.CommentRepository;
import org.example.blogsystem_lab11.Repository.PostRepository;
import org.example.blogsystem_lab11.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<Comment> getAll() { return commentRepository.findAll(); }

    public void addComment(Comment comment) {
        if (userRepository.giveMeUserById(comment.getUserId()) == null) throw new ApiException("User ID not found");
        if (postRepository.giveMePostById(comment.getPostId()) == null) throw new ApiException("Post ID not found");
        commentRepository.save(comment);
    }

    public void updateComment(Integer id, Comment comment) {
        Comment old = commentRepository.giveMeByCommentId(id);
        if (old == null) throw new ApiException("Comment not found");
        if (userRepository.giveMeUserById(comment.getUserId()) == null) throw new ApiException("User ID not found");
        if (postRepository.giveMePostById(comment.getPostId()) == null) throw new ApiException("Post ID not found");

        old.setContent(comment.getContent());
        old.setUserId(comment.getUserId());
        old.setPostId(comment.getPostId());
        commentRepository.save(old);
    }

    public void deleteComment(Integer id) {
        Comment comment = commentRepository.giveMeByCommentId(id);
        if (comment == null) throw new ApiException("Comment not found");
        commentRepository.delete(comment);
    }

    //Extra
    public List<Comment> getCommentsByPostId(Integer postId) {
        if (postRepository.giveMePostById(postId) == null) throw new ApiException("Post not found");
        List<Comment> comments = commentRepository.giveMeAllByPostId(postId);
        if (comments.isEmpty()) throw new ApiException("No comments found for this post");
        return comments;
    }
    public Integer getCommentCount(Integer postId) {
        if (postRepository.giveMePostById(postId) == null) {
            throw new ApiException("Post not found to count its comments");
        }
        return commentRepository.giveMeCountCommentsByPostId(postId);
    }

}


