package com.inditex.prueba.repository;

import com.inditex.prueba.repository.dto.PriceDto;
import com.inditex.prueba.repository.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository
    extends JpaRepository<Price, Long> /*, JpaSpecificationExecutor<Price> */ {

  @Query(
      value =
          """
    SELECT new com.inditex.prueba.repository.dto.PriceDto(
    p.product, b.name, pro.rate, p.startDate, p.endDate, p.price
    )
    FROM Price p
    INNER JOIN p.brand b
    INNER JOIN p.product pro
    WHERE (:date IS NULL OR :date BETWEEN p.startDate AND p.endDate)
    AND (:priceList IS NULL OR p.priceList = :priceList)
    AND (:brandId IS NULL OR b.id = :brandId)
""")
  List<PriceDto> findResults(
      @Param("date") LocalDateTime date,
      @Param("priceList") Long priceList,
      @Param("brandId") Long brandId);
}
