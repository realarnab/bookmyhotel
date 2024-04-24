package com.bookmyhotel.service;

import com.bookmyhotel.dto.BookingDto;
import com.bookmyhotel.entity.Booking;
import com.bookmyhotel.entity.PropertyUser;

import javax.print.attribute.standard.PrinterURI;
import java.util.List;

public interface BookingService {
    public BookingDto addBooking(BookingDto dto, PropertyUser user,long propertyId);
    public List<Booking> getAllBookingsByUser(PropertyUser user);
}
