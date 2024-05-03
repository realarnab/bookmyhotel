package com.bookmyhotel.exceptions;

import com.bookmyhotel.dto.ErrorDetails;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice //make this class as global exception handler class for the application
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<?> handleCountryNotFound(CountryNotFoundException e, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),e.getMessage(),webRequest.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PropertyUserNotFoundException.class)
    public ResponseEntity<?> handlePropertyUserNotFound(PropertyUserNotFoundException e,WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),e.getMessage(),webRequest.getDescription(true));
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PropertyNotFound.class)
    public ResponseEntity<?> handlePropertyNotFound(PropertyNotFound e, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),e.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAnyException(Exception e,WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),e.getMessage(), webRequest.getDescription(true));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(FavouriteNotFoundException.class)
    public ResponseEntity<?> handleFavouriteNotFound(FavouriteNotFoundException e,WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),e.getMessage(),webRequest.getDescription(true));
        return new ResponseEntity<>(errorDetails,HttpStatus.OK);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<?> handleReviewNotFound(ReviewNotFoundException e,WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),e.getMessage(), webRequest.getDescription(true));
        return new ResponseEntity<>(errorDetails,HttpStatus.OK);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<?> handleUnauthorizedAccess(UnauthorizedAccessException e,WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),e.getMessage(),webRequest.getDescription(true));
        return new  ResponseEntity<>(errorDetails,HttpStatus.OK);
    }

}
