package com.inditex.prueba.service;

import com.inditex.prueba.controller.model.PriceRequest;
import com.inditex.prueba.controller.model.PriceResponse;
import com.inditex.prueba.repository.PriceRepository;
import com.inditex.prueba.repository.model.Brand;
import com.inditex.prueba.repository.model.Price;
import com.inditex.prueba.repository.model.Product;
import com.inditex.prueba.service.mapper.PriceMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

  @Mock private PriceRepository priceRepository;

  @Mock private PriceMapper priceMapper;
  @InjectMocks private PriceService priceService;


  @Test
  public void testFindApplicablePrices1() {
    LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
    Long productId = 35455L;
    Long brandId = 1L;

    PriceRequest priceRequest =
        PriceRequest.builder().brandId(brandId).productId(productId).date(date).build();

    List<Price> mockPrices =
        List.of(
            Price.builder()
                .brand(Brand.builder().id(1L).name("XYZ").build())
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(1L)
                .product(Product.builder().id(35455L).rate("standard").build())
                .priority(0)
                .price(new BigDecimal("35.50"))
                .currency("EUR")
                .build());

    when(priceRepository.findResults(
            priceRequest.getDate(), priceRequest.getProductId(), priceRequest.getBrandId()))
        .thenReturn(mockPrices);

    List<PriceResponse> mockPriceResponses =
        List.of(
            PriceResponse.builder()
                .productId(35455L)
                .chainId("XYZ")
                .rate("standard")
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .finalPrice(new BigDecimal("35.50"))
                .build());

    when(priceMapper.toPriceResponse(mockPrices.get(0))).thenReturn(mockPriceResponses.get(0));

    List<PriceResponse> result = priceService.findApplicablePrices(priceRequest);

    assertEquals(mockPriceResponses, result);
  }

  @Test
  public void testFindApplicablePrices2() {
    LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);
    Long productId = 35455L;
    Long brandId = 1L;

    PriceRequest priceRequest =
            PriceRequest.builder().brandId(brandId).productId(productId).date(date).build();

    List<Price> mockPrices =
            List.of(
                    Price.builder()
                            .brand(Brand.builder().id(1L).name("XYZ").build())
                            .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
                            .priceList(3L)
                            .product(Product.builder().id(35455L).rate("standard").build())
                            .priority(1)
                            .price(new BigDecimal("30.50"))
                            .currency("EUR")
                            .build(),

                    Price.builder()
                            .brand(Brand.builder().id(1L).name("XYZ").build())
                            .startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                            .priceList(4L)
                            .product(Product.builder().id(35455L).rate("standard").build())
                            .priority(1)
                            .price(new BigDecimal("38.95"))
                            .currency("EUR")
                            .build(),

                    Price.builder()
                            .brand(Brand.builder().id(1L).name("XYZ").build())
                            .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                            .priceList(1L)
                            .product(Product.builder().id(35455L).rate("standard").build())
                            .priority(0)
                            .price(new BigDecimal("35.50"))
                            .currency("EUR")
                            .build(),

                    Price.builder()
                            .brand(Brand.builder().id(1L).name("XYZ").build())
                            .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 14, 18, 30, 0))
                            .priceList(2L)
                            .product(Product.builder().id(35455L).rate("standard").build())
                            .priority(0)
                            .price(new BigDecimal("25.45"))
                            .currency("EUR")
                            .build()
            );

    when(priceRepository.findResults(
            priceRequest.getDate(), priceRequest.getProductId(), priceRequest.getBrandId()))
            .thenReturn(mockPrices);

    List<PriceResponse> mockPriceResponses =
            List.of(
                    PriceResponse.builder()
                            .productId(35455L)
                            .chainId("XYZ")
                            .rate("standard")
                            .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
                            .finalPrice(new BigDecimal("30.50"))
                            .build(),

                    PriceResponse.builder()
                            .productId(35455L)
                            .chainId("XYZ")
                            .rate("standard")
                            .startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                            .finalPrice(new BigDecimal("38.95"))
                            .build(),

                    PriceResponse.builder()
                            .productId(35455L)
                            .chainId("XYZ")
                            .rate("standard")
                            .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                            .finalPrice(new BigDecimal("35.50"))
                            .build(),

                    PriceResponse.builder()
                            .productId(35455L)
                            .chainId("XYZ")
                            .rate("standard")
                            .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 14, 18, 30, 0))
                            .finalPrice(new BigDecimal("25.45"))
                            .build());

    when(priceMapper.toPriceResponse(mockPrices.get(0))).thenReturn(mockPriceResponses.get(0));
    when(priceMapper.toPriceResponse(mockPrices.get(1))).thenReturn(mockPriceResponses.get(1));
    when(priceMapper.toPriceResponse(mockPrices.get(2))).thenReturn(mockPriceResponses.get(2));
    when(priceMapper.toPriceResponse(mockPrices.get(3))).thenReturn(mockPriceResponses.get(3));

    List<PriceResponse> result = priceService.findApplicablePrices(priceRequest);

    assertEquals(mockPriceResponses, result);
  }

  @Test
  public void testFindApplicablePrices3() {
    LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 0);
    Long productId = 35455L;
    Long brandId = 1L;

    PriceRequest priceRequest =
            PriceRequest.builder().brandId(brandId).productId(productId).date(date).build();

    List<Price> mockPrices =
            List.of(
                    Price.builder()
                            .brand(Brand.builder().id(1L).name("XYZ").build())
                            .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
                            .priceList(3L)
                            .product(Product.builder().id(35455L).rate("standard").build())
                            .priority(1)
                            .price(new BigDecimal("30.50"))
                            .currency("EUR")
                            .build(),

                    Price.builder()
                            .brand(Brand.builder().id(1L).name("XYZ").build())
                            .startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                            .priceList(4L)
                            .product(Product.builder().id(35455L).rate("standard").build())
                            .priority(1)
                            .price(new BigDecimal("38.95"))
                            .currency("EUR")
                            .build(),

                    Price.builder()
                            .brand(Brand.builder().id(1L).name("XYZ").build())
                            .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                            .priceList(1L)
                            .product(Product.builder().id(35455L).rate("standard").build())
                            .priority(0)
                            .price(new BigDecimal("35.50"))
                            .currency("EUR")
                            .build(),

                    Price.builder()
                            .brand(Brand.builder().id(1L).name("XYZ").build())
                            .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 14, 18, 30, 0))
                            .priceList(2L)
                            .product(Product.builder().id(35455L).rate("standard").build())
                            .priority(0)
                            .price(new BigDecimal("25.45"))
                            .currency("EUR")
                            .build()
            );

    when(priceRepository.findResults(
            priceRequest.getDate(), priceRequest.getProductId(), priceRequest.getBrandId()))
            .thenReturn(mockPrices);

    List<PriceResponse> mockPriceResponses =
            List.of(
                    PriceResponse.builder()
                            .productId(35455L)
                            .chainId("XYZ")
                            .rate("standard")
                            .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
                            .finalPrice(new BigDecimal("30.50"))
                            .build(),

                    PriceResponse.builder()
                            .productId(35455L)
                            .chainId("XYZ")
                            .rate("standard")
                            .startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                            .finalPrice(new BigDecimal("38.95"))
                            .build(),

                    PriceResponse.builder()
                            .productId(35455L)
                            .chainId("XYZ")
                            .rate("standard")
                            .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                            .finalPrice(new BigDecimal("35.50"))
                            .build(),

                    PriceResponse.builder()
                            .productId(35455L)
                            .chainId("XYZ")
                            .rate("standard")
                            .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 14, 18, 30, 0))
                            .finalPrice(new BigDecimal("25.45"))
                            .build());

    when(priceMapper.toPriceResponse(mockPrices.get(0))).thenReturn(mockPriceResponses.get(0));
    when(priceMapper.toPriceResponse(mockPrices.get(1))).thenReturn(mockPriceResponses.get(1));
    when(priceMapper.toPriceResponse(mockPrices.get(2))).thenReturn(mockPriceResponses.get(2));
    when(priceMapper.toPriceResponse(mockPrices.get(3))).thenReturn(mockPriceResponses.get(3));

    List<PriceResponse> result = priceService.findApplicablePrices(priceRequest);

    assertEquals(mockPriceResponses, result);
  }

  @Test
  public void testFindApplicablePrices4() {
    LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0);
    Long productId = 35455L;
    Long brandId = 1L;

    PriceRequest priceRequest =
            PriceRequest.builder().brandId(brandId).productId(productId).date(date).build();

    List<Price> mockPrices =
            List.of(
                    Price.builder()
                            .brand(Brand.builder().id(1L).name("XYZ").build())
                            .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
                            .priceList(3L)
                            .product(Product.builder().id(35455L).rate("standard").build())
                            .priority(1)
                            .price(new BigDecimal("30.50"))
                            .currency("EUR")
                            .build(),

                    Price.builder()
                            .brand(Brand.builder().id(1L).name("XYZ").build())
                            .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                            .priceList(1L)
                            .product(Product.builder().id(35455L).rate("standard").build())
                            .priority(0)
                            .price(new BigDecimal("35.50"))
                            .currency("EUR")
                            .build(),

                    Price.builder()
                            .brand(Brand.builder().id(1L).name("XYZ").build())
                            .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 14, 18, 30, 0))
                            .priceList(2L)
                            .product(Product.builder().id(35455L).rate("standard").build())
                            .priority(0)
                            .price(new BigDecimal("25.45"))
                            .currency("EUR")
                            .build()
            );

    when(priceRepository.findResults(
            priceRequest.getDate(), priceRequest.getProductId(), priceRequest.getBrandId()))
            .thenReturn(mockPrices);

    List<PriceResponse> mockPriceResponses =
            List.of(
                    PriceResponse.builder()
                            .productId(35455L)
                            .chainId("XYZ")
                            .rate("standard")
                            .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
                            .finalPrice(new BigDecimal("30.50"))
                            .build(),

                    PriceResponse.builder()
                            .productId(35455L)
                            .chainId("XYZ")
                            .rate("standard")
                            .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                            .finalPrice(new BigDecimal("35.50"))
                            .build(),

                    PriceResponse.builder()
                            .productId(35455L)
                            .chainId("XYZ")
                            .rate("standard")
                            .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 14, 18, 30, 0))
                            .finalPrice(new BigDecimal("25.45"))
                            .build());

    when(priceMapper.toPriceResponse(mockPrices.get(0))).thenReturn(mockPriceResponses.get(0));
    when(priceMapper.toPriceResponse(mockPrices.get(1))).thenReturn(mockPriceResponses.get(1));
    when(priceMapper.toPriceResponse(mockPrices.get(2))).thenReturn(mockPriceResponses.get(2));

    List<PriceResponse> result = priceService.findApplicablePrices(priceRequest);

    assertEquals(mockPriceResponses, result);
  }

  @Test
  public void testFindApplicablePrices5() {
    LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 0);
    Long productId = 35455L;
    Long brandId = 1L;

    PriceRequest priceRequest =
            PriceRequest.builder().brandId(brandId).productId(productId).date(date).build();

    List<Price> mockPrices =
            List.of(
                    Price.builder()
                            .brand(Brand.builder().id(1L).name("XYZ").build())
                            .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
                            .priceList(3L)
                            .product(Product.builder().id(35455L).rate("standard").build())
                            .priority(1)
                            .price(new BigDecimal("30.50"))
                            .currency("EUR")
                            .build(),

                    Price.builder()
                            .brand(Brand.builder().id(1L).name("XYZ").build())
                            .startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                            .priceList(4L)
                            .product(Product.builder().id(35455L).rate("standard").build())
                            .priority(1)
                            .price(new BigDecimal("38.95"))
                            .currency("EUR")
                            .build(),

                    Price.builder()
                            .brand(Brand.builder().id(1L).name("XYZ").build())
                            .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                            .priceList(1L)
                            .product(Product.builder().id(35455L).rate("standard").build())
                            .priority(0)
                            .price(new BigDecimal("35.50"))
                            .currency("EUR")
                            .build(),

                    Price.builder()
                            .brand(Brand.builder().id(1L).name("XYZ").build())
                            .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 14, 18, 30, 0))
                            .priceList(2L)
                            .product(Product.builder().id(35455L).rate("standard").build())
                            .priority(0)
                            .price(new BigDecimal("25.45"))
                            .currency("EUR")
                            .build()
            );

    when(priceRepository.findResults(
            priceRequest.getDate(), priceRequest.getProductId(), priceRequest.getBrandId()))
            .thenReturn(mockPrices);

    List<PriceResponse> mockPriceResponses =
            List.of(
                    PriceResponse.builder()
                            .productId(35455L)
                            .chainId("XYZ")
                            .rate("standard")
                            .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
                            .finalPrice(new BigDecimal("30.50"))
                            .build(),

                    PriceResponse.builder()
                            .productId(35455L)
                            .chainId("XYZ")
                            .rate("standard")
                            .startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                            .finalPrice(new BigDecimal("38.95"))
                            .build(),

                    PriceResponse.builder()
                            .productId(35455L)
                            .chainId("XYZ")
                            .rate("standard")
                            .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                            .finalPrice(new BigDecimal("35.50"))
                            .build(),

                    PriceResponse.builder()
                            .productId(35455L)
                            .chainId("XYZ")
                            .rate("standard")
                            .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                            .endDate(LocalDateTime.of(2020, 12, 14, 18, 30, 0))
                            .finalPrice(new BigDecimal("25.45"))
                            .build());

    when(priceMapper.toPriceResponse(mockPrices.get(0))).thenReturn(mockPriceResponses.get(0));
    when(priceMapper.toPriceResponse(mockPrices.get(1))).thenReturn(mockPriceResponses.get(1));
    when(priceMapper.toPriceResponse(mockPrices.get(2))).thenReturn(mockPriceResponses.get(2));
    when(priceMapper.toPriceResponse(mockPrices.get(3))).thenReturn(mockPriceResponses.get(3));

    List<PriceResponse> result = priceService.findApplicablePrices(priceRequest);

    assertEquals(mockPriceResponses, result);
  }
}

