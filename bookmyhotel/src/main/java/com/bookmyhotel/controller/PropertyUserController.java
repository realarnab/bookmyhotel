package com.bookmyhotel.controller;

import com.bookmyhotel.dto.LoginDto;
import com.bookmyhotel.dto.PropertyUserDto;
import com.bookmyhotel.dto.TokenResponse;
import com.bookmyhotel.entity.PropertyUser;
import com.bookmyhotel.service.EmailService;
import com.bookmyhotel.service.PropertyUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class PropertyUserController {
    private PropertyUserService propertyUserService;
    private EmailService emailService;

    public PropertyUserController(PropertyUserService propertyUserService, EmailService emailService) {
        this.propertyUserService = propertyUserService;
        this.emailService = emailService;
    }


    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody PropertyUserDto propertyUserDto){
        PropertyUser user = propertyUserService.addUser(propertyUserDto);
        if (user!=null){
            emailService.sendMail(user.getEmail(),"Registration Confirmation","Account Registration Done with BookMyHotel");
            return new ResponseEntity<>("Registration is successful", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Registration failed!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        String token= propertyUserService.verifyLogin(loginDto);

        if (token!=null){
            TokenResponse tokenResponse=new TokenResponse();
            tokenResponse.setToken(token);
            return new ResponseEntity<>(tokenResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid credentials",HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        return new ResponseEntity<>("Logout successfully",HttpStatus.OK);
    }

    @GetMapping("/profile") //this method is used for getting the user details of active profile(log in)
    public PropertyUser getCurrentUserProfile(@AuthenticationPrincipal PropertyUser user){
        return user;
    }


}
