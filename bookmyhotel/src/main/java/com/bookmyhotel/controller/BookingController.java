package com.bookmyhotel.controller;

import com.bookmyhotel.dto.BookingDto;
import com.bookmyhotel.entity.Booking;
import com.bookmyhotel.entity.PropertyUser;
import com.bookmyhotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/addNew/{propertyId}")
    public ResponseEntity<BookingDto> newBooking(@RequestBody BookingDto bookingDto, @AuthenticationPrincipal PropertyUser propertyUser, @PathVariable long propertyId){
        BookingDto saved = bookingService.addBooking(bookingDto, propertyUser,propertyId);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/getBooking")
    public ResponseEntity<?> getAllBookingsOfUser(@AuthenticationPrincipal PropertyUser propertyUser){
        List<Booking> allBookings = bookingService.getAllBookingsByUser(propertyUser);
        return new ResponseEntity<>(allBookings,HttpStatus.OK);
    }

    @DeleteMapping("/deleteBooking/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable long id, @AuthenticationPrincipal PropertyUser propertyUser){
        bookingService.deleteBooking(propertyUser,id);
        return new ResponseEntity<>("Booking cancel successfully",HttpStatus.OK);
    }

}
