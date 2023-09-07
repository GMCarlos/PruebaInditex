package com.inditex.prueba.repository.model;

import jakarta.persistence.*;

import lombok.*;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "brand")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
}
