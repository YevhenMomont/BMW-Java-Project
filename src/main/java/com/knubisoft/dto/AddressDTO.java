package com.knubisoft.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressDTO {

    private String street;
    private String suite;
    private String city;
    @JsonProperty(value = "zipcode")
    private String zipCode;
    private GeoDTO geo;
}
