package com.bookmyhotel.service.impl;

import com.bookmyhotel.dto.LoginDto;
import com.bookmyhotel.dto.PropertyUserDto;
import com.bookmyhotel.entity.PropertyUser;
import com.bookmyhotel.exceptions.PropertyUserNotFoundException;
import com.bookmyhotel.repository.PropertyUserRepository;
import com.bookmyhotel.service.JWTService;
import com.bookmyhotel.service.PropertyUserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertyUserServiceImpl implements PropertyUserService {

    private PropertyUserRepository propertyUserRepository;
    private JWTService jwtService; //to implement JWT token based authentication mechanism

    public PropertyUserServiceImpl(PropertyUserRepository propertyUserRepository, JWTService jwtService) {
        this.propertyUserRepository = propertyUserRepository;
        this.jwtService=jwtService;
    }

    public PropertyUser addUser(PropertyUserDto propertyUserDto){ //add the user to the database
        PropertyUser user = new PropertyUser();
        //set the details to the entity class object
        user.setFirstName(propertyUserDto.getFirstName());
        user.setLastName(propertyUserDto.getLastName());
        user.setEmail(propertyUserDto.getEmail());
        user.setUsername(propertyUserDto.getUsername());
        //user.setPassword(new BCryptPasswordEncoder().encode(propertyUserDto.getPassword())); -->this is also a technique to encrypt the password
        user.setPassword(BCrypt.hashpw(propertyUserDto.getPassword(),BCrypt.gensalt(10))); //-->it will encrypt the password with providing 10 rounds of salting before hashing it
        //by encrypting the password this way, it will reduce the chance of brut-force attack(this attacks use to decrypt the password)
        user.setUserRole(propertyUserDto.getUserRole());
        PropertyUser saved = propertyUserRepository.save(user);
        return saved;
    }

    @Override
    public String verifyLogin(LoginDto loginDto) { //this method is used to develop a log in feature
        PropertyUser user = propertyUserRepository.findByUsername(loginDto.getUsername()).orElseThrow(()->new PropertyUserNotFoundException("Property User not found with this Username:"+loginDto.getUsername())); //get the data from the database using username
            boolean check = BCrypt.checkpw(loginDto.getPassword(), user.getPassword()); //checkpw() -->this method will compare the expected  password and actual password and if matched it will  return boolean(true)
            if (check){ //if the password matches then it will be true
               return jwtService.generateToken(user); //call this method to generate JWT and return the Token with user details as a String
            }

        return null;
    }
}
