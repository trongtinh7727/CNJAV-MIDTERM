package com.iiex.lab7_lt.Repository;

import com.iiex.lab7_lt.Model.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
