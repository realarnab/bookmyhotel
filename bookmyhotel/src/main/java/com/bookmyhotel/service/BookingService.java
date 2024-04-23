package com.bookmyhotel.service;

import com.bookmyhotel.dto.BookingDto;
import com.bookmyhotel.entity.PropertyUser;

public interface BookingService {
    public BookingDto addBooking(BookingDto dto, PropertyUser user,long propertyId);
}
