package com.xemyru.naryanonline.dto.filter;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HouseDto {
    private Integer id;
    private String title;
    private String description;
    private BigDecimal price;
    private String location;
    private Integer categoryId;
    private String imagePath;
    private Integer userId;
    private boolean enabled;
}