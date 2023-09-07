package com.inditex.prueba.controller.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PriceRequest {

  private LocalDateTime date;

  private Long productId;

  private Long brandId;
}
