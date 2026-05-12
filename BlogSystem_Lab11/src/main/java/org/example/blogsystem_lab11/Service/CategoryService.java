package org.example.blogsystem_lab11.Service;

import lombok.RequiredArgsConstructor;
import org.example.blogsystem_lab11.Api.ApiException;
import org.example.blogsystem_lab11.Model.Category;
import org.example.blogsystem_lab11.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAll() { return categoryRepository.findAll(); }

    public void addCategory(Category category) { categoryRepository.save(category); }

    public void updateCategory(Integer id, Category category) {
        Category old = categoryRepository.giveMeCategoryById(id);
        if (old == null) throw new ApiException("Category not found");
        old.setName(category.getName());
        categoryRepository.save(old);
    }

    public void deleteCategory(Integer id) {
        Category category = categoryRepository.giveMeCategoryById(id);
        if (category == null) throw new ApiException("Category not found");
        categoryRepository.delete(category);
    }
}
