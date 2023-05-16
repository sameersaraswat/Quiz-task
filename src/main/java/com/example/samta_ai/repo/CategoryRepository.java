package com.example.samta_ai.repo;

import com.example.samta_ai.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
