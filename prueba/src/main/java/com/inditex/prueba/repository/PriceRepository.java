package com.inditex.prueba.repository;

import com.inditex.prueba.repository.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository
    extends JpaRepository<Price, Long> {

  @Query(
      value =
          """
    SELECT p
    FROM Price p
    INNER JOIN p.brand b
    INNER JOIN p.product pro
    WHERE (:date IS NULL OR :date BETWEEN p.startDate AND p.endDate)
    AND (:productId IS NULL OR pro.id = :productId)
    AND (:brandId IS NULL OR b.id = :brandId)
    ORDER BY p.priority DESC
""")
  List<Price> findResults(
      @Param("date") LocalDateTime date,
      @Param("productId") Long priceList,
      @Param("brandId") Long brandId);
}
