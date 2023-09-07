package com.inditex.prueba.repository.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "price")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Price {

  @ManyToOne
  @JoinColumn(name = "brand_id")
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  @NotNull
  private Brand brand;

  @NotNull private LocalDateTime startDate;

  @NotNull private LocalDateTime endDate;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long priceList;

  @ManyToOne
  @JoinColumn(name = "product_id")
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  @NotNull
  private Product product;

  @NotNull private Integer priority;
  @NotNull private BigDecimal price;
  @NotNull
  @Size(min = 3, max = 3)
  private String currency;
}
