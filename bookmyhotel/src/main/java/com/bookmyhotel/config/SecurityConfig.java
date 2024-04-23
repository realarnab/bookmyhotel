package com.bookmyhotel.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter; //this class  should come from this package only

@Configuration //this class is responsible for implementing th security features in the application
//After the starting of your application configuration class will run first and this class which implement the security features in our application that's why it needs to be run first
public class SecurityConfig {

    @Autowired
    private JWTRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{ // SecurityFilterChain --> this interface feature to apply chain of security mechanism in your project
        //HttpSecurity --> this final class provide the http object by which we can perform chain of security mechanism in our application
        http.csrf().disable().cors().disable(); //disable the CSRF and CORS protection because it's a stateless security mechanism, and we use Postman as a client
        http.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class); //it will take the to doFilter() method and it takes the http request to the filtration process where we verify the user details from the JWT token
        // http.authorizeHttpRequests().anyRequest().permitAll(); // Allow all requests to be permitted
        http.authorizeHttpRequests()
                .requestMatchers("/api/v1/users/addUser","/api/v1/users/login").permitAll() //this type of request shouldn't be authenticated, it is open for all
                .requestMatchers("/api/v1/countries/addCountry","/api/v1/countries/deleteCountry/{id}","/api/v1/favourite","/api/v1/properties/addProperty",
                        "/api/v1/properties/deleteProperty/{id}","/api/v1/images/upload/{bucketName}/property/{propertyId}","/s3bucket/upload/file/{bucketName}").hasRole("ADMIN") //makes the url only accessible for ADMIN
                .requestMatchers("/api/v1/properties/{locationName}","/api/v1/reviews/addReview/{propertyId}").hasRole("USER")
                .requestMatchers("api/v1/users/profile","/api/v1/booking/addNew/{propertyId}").hasAnyRole("ADMIN","USER") //makes the url accessible for ADMIN and USER
                .requestMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated(); //any other type of request rather than above should be authenticated
        return http.build(); //Build and return the object of securityFilterChain
    }

    //create the bean for the ModelMapper
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
