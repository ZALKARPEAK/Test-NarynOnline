package com.xemyru.naryanonline.controller;

import com.xemyru.naryanonline.dto.filter.HouseDto;
import com.xemyru.naryanonline.dto.filter.HouseFilterRequest;
import com.xemyru.naryanonline.service.house.HousesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/houses")
@Tag(name = "House", description = "Operations about house")
public class HouseController {

    private final HousesService housesService;

    @GetMapping
    public ResponseEntity<List<HouseDto>> getAllHouses(@RequestParam(required = false) Long categoryId) {
        List<HouseDto> houses = housesService.getAllHouses(categoryId);
        return ResponseEntity.ok(houses);
    }

    @GetMapping("/housesFilter")
    @Operation(summary = "Get all houses with filter",
            description = "Get all houses with filter," +
                    " based on title, description," +
                    " minPrice, maxPrice, location," +
                    " categoryId"
            , tags = {"Houses Management"}
    )
    public ResponseEntity<List<HouseDto>> getHousesFilter(@RequestBody HouseFilterRequest houseFilterDto) {
        return ResponseEntity.ok(housesService.getAllHousesWithFilter(houseFilterDto));
    }
}