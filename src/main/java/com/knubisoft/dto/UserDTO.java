package com.knubisoft.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Email;
import lombok.Data;

@Data
public class UserDTO {

    private Integer id;
    private String name;
    @JsonProperty(value = "username")
    private String userName;
    @Email
    private String email;
    private AddressDTO address;
    private String phone;
    private String website;
    private CompanyDTO company;
}
