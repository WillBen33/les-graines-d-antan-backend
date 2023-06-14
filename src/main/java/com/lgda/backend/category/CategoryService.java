package com.lgda.backend.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " not found"));
    }

    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category category, Long id) {
        Category foundCategory = getById(id);

        foundCategory.setName(category.getName());

        return categoryRepository.save(foundCategory);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

}
