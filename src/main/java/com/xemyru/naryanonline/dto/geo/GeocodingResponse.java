package com.xemyru.naryanonline.dto.geo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GeocodingResponse {
    private List<GeocodingResult> results;
}