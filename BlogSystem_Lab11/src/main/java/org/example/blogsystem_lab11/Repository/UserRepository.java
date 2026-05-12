package org.example.blogsystem_lab11.Repository;

import org.example.blogsystem_lab11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.userId=?1")
    User giveMeUserById(Integer id);

    @Query("select u from User u where u.email = ?1")
    User giveMeUserByEmail(String email);

    @Query("select u from User u where u.username = ?1")
    User giveMeUserByUsername(String username);
}
