package com.xemyru.naryanonline.service.house;


import com.xemyru.naryanonline.dto.filter.HouseDto;
import com.xemyru.naryanonline.dto.filter.HouseFilterRequest;
import com.xemyru.naryanonline.filter.HousesFilter;
import com.xemyru.naryanonline.mapper.HouseMapper;
import com.xemyru.naryanonline.repository.house.HouseRepository;
import com.xemyru.naryanonline.tables.records.HousesRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class HousesServiceImpl implements HousesService {

    private final HouseRepository houseRepository;
    private final HousesFilter housesFilter;
    private final HouseMapper houseMapper;

    @Override
    public List<HouseDto> getAllHousesWithFilter(HouseFilterRequest filterHouse) {
        log.info("Getting all houses with filter: {}", filterHouse);

        Stream<HousesRecord> filteredHouses = housesFilter.filter(filterHouse);

        return filteredHouses
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private HouseDto convertToDto(HousesRecord houseRecord) {
        HouseDto houseDto = new HouseDto();
        houseDto.setTitle(houseRecord.getTitle());
        houseDto.setDescription(houseRecord.getDescription());
        houseDto.setPrice(houseRecord.getPrice());
        houseDto.setLocation(houseRecord.getLocation());
        return houseDto;
    }

    @Override
    public List<HouseDto> getAllHouses(Long categoryId) {
        List<HousesRecord> housesRecords = houseRepository.findAllByCategoryId(categoryId);

        return houseMapper.toHouseListDto(housesRecords);
    }
}
