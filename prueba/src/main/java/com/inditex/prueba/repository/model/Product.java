package com.inditex.prueba.repository.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String rate;
}
