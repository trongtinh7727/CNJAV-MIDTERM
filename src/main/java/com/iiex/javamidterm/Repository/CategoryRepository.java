package com.iiex.javamidterm.Repository;

import com.iiex.javamidterm.Model.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
