package com.bookmyhotel.dto;

import com.bookmyhotel.entity.Property;
import com.bookmyhotel.entity.PropertyUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer totalPrice;
    private Integer totalNights;
    private String guestName;
    private PropertyUser propertyUser;
    private Property property;

}
