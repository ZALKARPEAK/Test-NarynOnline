package com.xemyru.naryanonline.filter;

import com.xemyru.naryanonline.dto.filter.HouseFilterRequest;
import com.xemyru.naryanonline.repository.house.HouseRepository;
import com.xemyru.naryanonline.tables.records.HousesRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class HousesFilter extends HouseFilterRequest {

    private final HouseRepository repository;

    public Stream<HousesRecord> filter(HouseFilterRequest filters) {
        return repository.getAllHouse().stream()
               .filter(house -> applyFilters(house, filters));
    }

    private boolean applyFilters(HousesRecord house, HouseFilterRequest filters) {
        boolean matches = true;

        if (filters.getTitle() != null && !filters.getTitle().isEmpty()) {
            matches = house.getTitle().contains(filters.getTitle());
        }

        if (filters.getDescription() != null && !filters.getDescription().isEmpty()) {
            matches = matches && house.getDescription().contains(filters.getDescription());
        }

        if (filters.getMinPrice() != null ) {
            matches = matches && house.getPrice().compareTo(filters.getMinPrice()) >= 0;
        }

        if (filters.getMaxPrice() != null) {
            matches = matches && house.getPrice().compareTo(filters.getMaxPrice()) <= 0;
        }

        if (filters.getLocation() != null && !filters.getLocation().isEmpty()) {
            matches = matches && house.getLocation().contains(filters.getLocation());
        }

        if (filters.getCategoryId() != null) {
            matches = matches && house.getCategoryId().equals(filters.getCategoryId());
        }

        return matches;
    }
}