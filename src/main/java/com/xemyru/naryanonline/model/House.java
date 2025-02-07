package com.xemyru.naryanonline.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    @NotBlank
    private String location;
    @NotBlank
    private Long categoryId;
    @NotBlank
    private Long userId;
    private Boolean enabled;
}
