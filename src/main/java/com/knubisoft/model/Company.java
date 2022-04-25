package com.knubisoft.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Company {

    private Integer id;
    private String name;
    private String catchPhrase;
    private String bs;

}
