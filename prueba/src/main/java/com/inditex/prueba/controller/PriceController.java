package com.inditex.prueba.controller;

import com.inditex.prueba.controller.model.PriceRequest;
import com.inditex.prueba.controller.model.PriceResponse;
import com.inditex.prueba.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceController {
  private final PriceService priceService;

  @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PriceResponse>> findPrices(@Valid PriceRequest priceRequest) {
    return ResponseEntity.ok(priceService.findApplicablePrices(priceRequest));
  }
}
