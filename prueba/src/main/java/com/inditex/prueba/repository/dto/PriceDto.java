package com.inditex.prueba.repository.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceDto {
    private Long productId;     //Price.productId

    private String chainId;         //Brand.name

    private String rate;         //Product.rate

    private LocalDateTime startDate;    //Price.startDate

    private LocalDateTime endDate;      //Price.endDate

    private BigDecimal finalPrice;  //Price.price


}
