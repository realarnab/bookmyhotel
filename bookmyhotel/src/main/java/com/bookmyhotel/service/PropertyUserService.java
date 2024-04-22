package com.bookmyhotel.service;

import com.bookmyhotel.dto.LoginDto;
import com.bookmyhotel.dto.PropertyUserDto;
import com.bookmyhotel.entity.PropertyUser;

public interface PropertyUserService {
    PropertyUser addUser(PropertyUserDto propertyUserDto);

    String verifyLogin(LoginDto loginDto); //this method, we will use during the login feature
}
