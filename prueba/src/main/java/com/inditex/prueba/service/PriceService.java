package com.inditex.prueba.service;

import com.inditex.prueba.controller.model.PriceRequest;
import com.inditex.prueba.repository.PriceRepository;
import com.inditex.prueba.repository.dto.PriceDto;
import com.inditex.prueba.repository.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceService {

  private final PriceRepository priceRepository;

  public List<PriceDto> findApplicablePrices(PriceRequest priceRequest) {

    return priceRepository.findResults(priceRequest.getDate(),
            priceRequest.getProductId(),
            priceRequest.getBrandId());
  }
}
