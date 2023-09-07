package com.inditex.prueba.service.mapper;

import com.inditex.prueba.controller.model.PriceResponse;
import com.inditex.prueba.repository.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  @Mapping(target = "productId", source = "price.product.id")
  @Mapping(target = "chainId", source = "price.brand.name")
  @Mapping(target = "rate", source = "price.product.rate")
  @Mapping(target = "finalPrice", source = "price.price")
  PriceResponse toPriceResponse(Price price);
}
