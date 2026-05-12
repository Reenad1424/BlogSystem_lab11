package org.example.blogsystem_lab11.Repository;

import org.example.blogsystem_lab11.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    @Query("select c from Category c where c.categoryId=?1")
    Category giveMeCategoryById(Integer id);
}
