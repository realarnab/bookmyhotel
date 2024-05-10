package com.bookmyhotel.dto;

import com.bookmyhotel.entity.Property;
import com.bookmyhotel.entity.PropertyUser;
import jakarta.validation.constraints.*;
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
    private Double totalPrice;

    @NotEmpty
    @Positive
    private Integer totalNights;

    @NotNull
    private String guestName;

    @NotNull
    @Size(min = 10,max = 10)
    private String guestMobile;

    @Email
    private String guestEmail;
    private PropertyUser propertyUser;
    private Property property;

}
