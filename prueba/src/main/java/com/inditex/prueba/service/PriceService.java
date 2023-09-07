package com.inditex.prueba.service;

import com.inditex.prueba.controller.model.PriceRequest;
import com.inditex.prueba.controller.model.PriceResponse;
import com.inditex.prueba.repository.PriceRepository;
import com.inditex.prueba.repository.model.Price;
import com.inditex.prueba.service.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceService {

  private final PriceRepository priceRepository;

  private final PriceMapper priceMapper;

  public List<PriceResponse> findApplicablePrices(PriceRequest priceRequest) {

    return priceRepository
        .findResults(priceRequest.getDate(), priceRequest.getProductId(), priceRequest.getBrandId())
        .stream()
        .map(this::getPriceResponseMapper)
        .toList();
  }

  private PriceResponse getPriceResponseMapper(Price price) {
    return priceMapper.toPriceResponse(price);
  }
}
