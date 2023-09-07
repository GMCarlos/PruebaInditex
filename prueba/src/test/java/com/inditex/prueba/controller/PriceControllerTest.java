package com.inditex.prueba.controller;

import com.inditex.prueba.controller.model.PriceRequest;
import com.inditex.prueba.controller.model.PriceResponse;
import com.inditex.prueba.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PriceControllerTest {

  private MockMvc mockMvc;

  @Mock private PriceService priceService;

  @InjectMocks private PriceController priceController;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(new PriceController(priceService)).build();
  }

  @Test
  public void testFindPricesOk() {
    LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
    Long productId = 35455L;
    Long brandId = 1L;

    PriceRequest priceRequest =
        PriceRequest.builder().brandId(brandId).productId(productId).date(date).build();
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

    when(priceService.findApplicablePrices(priceRequest)).thenReturn(mockPriceResponses);

    ResponseEntity<List<PriceResponse>> responseEntity = priceController.findPrices(priceRequest);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    assertEquals(mockPriceResponses, responseEntity.getBody());
  }

  @Test
  void shouldReturnOk() throws Exception {

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

    when(priceService.findApplicablePrices(any())).thenReturn(mockPriceResponses);

    final ResultActions resultActions =
        this.mockMvc.perform(get("/prices/find").contentType(MediaType.APPLICATION_JSON_VALUE));

    resultActions.andExpect(status().isOk());
  }
}
