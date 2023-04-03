package com.iiex.javamidterm.Repository;

import com.iiex.javamidterm.Model.Brand;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BrandRepository extends JpaRepository<Brand,Integer> {
}
