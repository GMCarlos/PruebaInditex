package com.inditex.prueba.controller.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PriceResponse {

    private Long productId;

    private String chainId;

    private String rate;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private BigDecimal finalPrice;

}
