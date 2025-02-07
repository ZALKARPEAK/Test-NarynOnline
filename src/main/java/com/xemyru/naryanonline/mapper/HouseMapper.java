package com.xemyru.naryanonline.mapper;

import com.xemyru.naryanonline.dto.filter.HouseDto;
import com.xemyru.naryanonline.tables.records.HousesRecord;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HouseMapper {
    HouseDto toHouseDto(HousesRecord housesRecord);

    List<HouseDto> toHouseListDto(List<HousesRecord> housesRecord);
}