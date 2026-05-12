package org.example.blogsystem_lab11.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.blogsystem_lab11.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    @Query("select c from Comment c where c.commentId=?1")
    Comment giveMeByCommentId(Integer id);

    //Extra
    @Query("select c from Comment c where c.postId = ?1")
    List<Comment> giveMeAllByPostId(Integer postId);

    @Query("select count(c) from Comment c where c.postId = ?1")
    Integer giveMeCountCommentsByPostId(Integer postId);


}
