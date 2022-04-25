package com.knubisoft.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    private Integer id;
    private String street;
    private String suite;
    private String city;
    private String zipCode;
    private Geo geo;

}
