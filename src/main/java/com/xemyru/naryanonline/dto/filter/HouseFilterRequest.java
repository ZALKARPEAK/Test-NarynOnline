package com.xemyru.naryanonline.dto.filter;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HouseFilterRequest {
    private String title;
    private String description;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String location;
    private Integer categoryId;
}