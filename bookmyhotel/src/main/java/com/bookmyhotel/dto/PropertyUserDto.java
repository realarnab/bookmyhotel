package com.bookmyhotel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyUserDto {
    private  String firstName;
    private String lastName;
    private String email;
    private String username;
    private  String password;
    private  String userRole;
}
