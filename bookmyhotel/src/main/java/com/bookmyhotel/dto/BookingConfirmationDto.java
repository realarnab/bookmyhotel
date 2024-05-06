package com.bookmyhotel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingConfirmationDto {
    private long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Double totalPrice;
    private Integer totalNights;
    private String guestName;
    private String guestMobile;
    private String guestEmail;
    private String propertyName;
}
