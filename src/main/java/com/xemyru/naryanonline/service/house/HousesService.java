package com.xemyru.naryanonline.service.house;

import com.xemyru.naryanonline.dto.filter.HouseDto;
import com.xemyru.naryanonline.dto.filter.HouseFilterRequest;

import java.util.List;

public interface HousesService {
    List<HouseDto> getAllHousesWithFilter(HouseFilterRequest filterHouse);
    List<HouseDto> getAllHouses(Long categoryId);
}
