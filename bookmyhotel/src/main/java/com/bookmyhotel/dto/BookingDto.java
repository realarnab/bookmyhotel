package com.bookmyhotel.dto;

import com.bookmyhotel.entity.Property;
import com.bookmyhotel.entity.PropertyUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private Integer totalPrice;
    private Integer totalNights;
    private String guestName;
    private PropertyUser propertyUser;
    private Property property;

}
