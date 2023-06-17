package com.lgda.backend.category;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable("id") Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    public Category add(@RequestBody Category category) {
        return categoryService.add(category);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable("id") Long id, @RequestBody Category category) {
        return categoryService.update(category, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
    }

}
