package com.bookmyhotel.controller;

import com.bookmyhotel.dto.BookingDto;
import com.bookmyhotel.entity.Booking;
import com.bookmyhotel.entity.PropertyUser;
import com.bookmyhotel.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private BucketService bucketService;
    @Autowired
    private SMSService smsService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private PDFService pdfService;
    @PostMapping("/addNew/{propertyId}")
    public ResponseEntity<?> newBooking(@RequestBody BookingDto bookingDto, @AuthenticationPrincipal PropertyUser propertyUser, @PathVariable long propertyId){
        BookingDto saved = bookingService.addBooking(bookingDto, propertyUser,propertyId);
        //create booking confirmation pdf
        String filePath ="C:\\Users\\arnab\\OneDrive\\Documents\\pdfBookmyhotel\\" + "booking-confirmation-id-" + saved.getId() + ".pdf";
        boolean status = pdfService.generatePdf(filePath, saved);
        if (status){
            try (FileInputStream inputStream = new FileInputStream(filePath)) {
                MultipartFile multipartFile = new MockMultipartFile("bookingConfirmation.pdf",new File(filePath).getName(),"application/pdf", inputStream);

                // Upload multipart file to S3 bucket
                String pdfUrl = bucketService.uploadFile(multipartFile, "bookmyhotel");
                //sent the booking details through sms
                smsService.sendSms("+916295486150","Your booking is confirmed with BookMyHotel. Click for more information "+pdfUrl);
                emailService.sendMail(saved.getGuestEmail(),"Booking Confirmed with BookMyHotel","Holiday Destination booking confirmation. "+pdfUrl);
            } catch (IOException e) {
                // Consider returning an error response or retry logic
            }
        }else {
            return new ResponseEntity<>("Something went wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
