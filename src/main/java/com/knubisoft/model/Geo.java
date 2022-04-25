package com.knubisoft.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Geo {

    private Double lat;
    private Double lng;

}
