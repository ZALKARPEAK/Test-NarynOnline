package com.xemyru.naryanonline.dto.filter;

import lombok.Data;

import java.math.BigDecimal;


public record HouseResponse(
        Integer id,
        String title,
        String description,
        BigDecimal price,
        String location,
        Integer categoryId,
        String imagePath,
        Integer userId,
        boolean enabled
) {
}
