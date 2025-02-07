package com.xemyru.naryanonline.service.routing;

import com.xemyru.naryanonline.dto.navigation.NavigationRoute;

public interface NavigationService {
    NavigationRoute calculateRoute(Integer houseId);
}
