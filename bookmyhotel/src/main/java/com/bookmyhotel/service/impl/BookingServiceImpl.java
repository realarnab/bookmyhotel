package com.bookmyhotel.service.impl;

import com.bookmyhotel.dto.BookingDto;
import com.bookmyhotel.entity.Booking;
import com.bookmyhotel.entity.Property;
import com.bookmyhotel.entity.PropertyUser;
import com.bookmyhotel.repository.BookingRepository;
import com.bookmyhotel.repository.PropertyRepository;
import com.bookmyhotel.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookingDto addBooking(BookingDto dto, PropertyUser user,long propertyId) {

        Booking booking = mapToEntity(dto);
        booking.setPropertyUser(user);

        Property property = propertyRepository.findById(propertyId).get();

        int nightlyPrice = property.getNightlyPrice();
        int totalNights = booking.getTotalNights();
        int total = nightlyPrice * totalNights;

        booking.setTotalPrice(total);
        booking.setProperty(property);
        Booking save = bookingRepository.save(booking);
        return mapToDto(save);
    }

    @Override
    public List<Booking> getAllBookingsByUser(PropertyUser user) {
        List<Booking> bookings = bookingRepository.findByPropertyUser(user);
        return bookings;
    }

    @Override
    public void deleteBooking(PropertyUser user, long id) {
        List<Booking> bookings = bookingRepository.findByPropertyUser(user);
//
//        int index = -1;
//        for (int i = 0; i <= bookings.size(); i++){
//            if (bookings.get(i).getId() == id){
//                index = i;
//                break;
//            }
//        }
//        if (index == -1){ //if booking not found, throw an exception
//            throw new RuntimeException("Booking not found");
//        }
//        //remove the booking from the list
//        bookings.remove(index);
        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()){
            Booking booking = iterator.next();
            if (booking.getId() == id){
                iterator.remove();
                return;
            }
        }
    }

    public Booking mapToEntity(BookingDto dto){
        return modelMapper.map(dto,Booking.class);
    }

    public BookingDto mapToDto(Booking booking){
        return modelMapper.map(booking,BookingDto.class);
    }
}
