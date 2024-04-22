package com.bookmyhotel.config;

import com.bookmyhotel.entity.PropertyUser;
import com.bookmyhotel.repository.PropertyUserRepository;
import com.bookmyhotel.service.JWTService;
import com.bookmyhotel.service.PropertyUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

//after the successful login and creation of JWT, later for every subsequent request made by the client it extracts the JWT from the Authorization header for further authentication and authorization  --> this is the significance of this class
@Component //JWTRequestFilter --> this class is responsible for performing filter on HTTP request
public class JWTRequestFilter extends OncePerRequestFilter { // OncePerRequestFilter --> it's an abstract class, it ensures that doFilterInternal method is only invoked once per request
    //After extends this class, for every subsequent request this class will be run once
    @Autowired
    private JWTService jwtService; //later used for extracting the Username of the client
    @Autowired
    private PropertyUserRepository propertyUserRepository;

    @Override //doFilterInternal method is overridden from OncePerRequestFilter class has the logic to be executed on each HTTP request
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //HttpServletRequest request --> is used to represent the HTTP request made by the client
        //HttpServletResponse response --> is used to represent the HTTP response which will be sent back to the client
        //FilterChain filterChain --> is used to represent the chain of filter that HTTP request should pass through
        String tokenHeader = request.getHeader("Authorization"); //it extracts the value of Authorization header from the HTTP request
        if (tokenHeader!=null && tokenHeader.startsWith("Bearer ")){ //it checks that tokenHeader is not null and starts with the word 'Bearer'
            String token = tokenHeader.substring(8,tokenHeader.length()-1); // to store the token from the header after eliminating the word 'Bearer' and store the header with in the single " ".
            //System.out.println(token);
            String userName = jwtService.getUserName(token); //extract the username from the token
            Optional<PropertyUser> opUser = propertyUserRepository.findByUsername(userName); //using that username, fetched the data from the database
            if (opUser.isPresent()){ //checking that content present in the object or not
                PropertyUser user = opUser.get(); //convert the optional object to entity object
                //after getting the user details, it will create the user session
                //this three lines of code snippet will authenticate the user based on the user credentials and create a unique session for each user
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user,null, Collections.singleton(new SimpleGrantedAuthority(user.getUserRole())));
                authenticationToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response); //to proceed the request for further processing
    }
}
