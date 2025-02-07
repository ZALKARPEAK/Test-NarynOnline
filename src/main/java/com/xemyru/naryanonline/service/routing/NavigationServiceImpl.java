package com.xemyru.naryanonline.service.routing;


import com.xemyru.naryanonline.dto.navigation.NavigationRoute;
import com.xemyru.naryanonline.repository.house.HouseRepository;
import com.xemyru.naryanonline.repository.user.UserRepository;
import com.xemyru.naryanonline.tables.records.HousesRecord;
import com.xemyru.naryanonline.tables.records.UsersRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NavigationServiceImpl implements NavigationService {

    private final UserRepository userRepository;
    private final HouseRepository houseRepository;
    private static final double EARTH_RADIUS = 6371.0;

    public NavigationRoute calculateRoute(Integer houseId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UsersRecord userRecord = userRepository.findByEmail(userDetails.getUsername());

        HousesRecord house = houseRepository.findById(houseId).orElseThrow(() -> new IllegalArgumentException("Invalid house id"));

        UsersRecord user = userRepository.findByEmail(userRecord.getEmail());

        double latARad = Math.toRadians(user.getLatitude());
        double latBRad = Math.toRadians(house.getLatitude());
        double deltaLat = Math.toRadians(house.getLatitude() - user.getLatitude());
        double deltaLon = Math.toRadians(house.getLongitude() - user.getLongitude());

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(latARad) * Math.cos(latBRad)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;

        double y = Math.sin(deltaLon) * Math.cos(latBRad);
        double x = Math.cos(latARad) * Math.sin(latBRad)
                - Math.sin(latARad) * Math.cos(latBRad) * Math.cos(deltaLon);
        double initialBearing = Math.toDegrees(Math.atan2(y, x));
        initialBearing = (initialBearing + 360) % 360;

        return new NavigationRoute(distance, initialBearing);
    }
}
