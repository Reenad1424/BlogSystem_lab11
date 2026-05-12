package org.example.blogsystem_lab11.Repository;

import org.example.blogsystem_lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    @Query("select p from Post p where p.postId=?1")
    Post giveMePostById(Integer id);

    //Extra
    @Query("select p from Post p where p.userId = ?1")
    List<Post> giveMeAllByUserId(Integer userId);

    @Query("select p from Post p where p.categoryId = ?1")
    List<Post> givMeAllByCategoryId(Integer categoryId);

    @Query("select p from Post p where p.title=?1")
    Post giveMePostByTitle(String title);

    @Query("select p from Post p where p.publishDate <= ?1")
    List<Post> giveMeAllPostsBeforeDate(LocalDate date);

    @Query("select p from Post p where p.content like %?1%")
    List<Post> giveMeByContent(String keyword);
}
