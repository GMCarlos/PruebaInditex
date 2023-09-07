package com.inditex.prueba.repository;

import com.inditex.prueba.repository.model.Brand;
import com.inditex.prueba.repository.model.Price;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
