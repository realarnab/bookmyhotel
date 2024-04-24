package com.bookmyhotel.repository;

import com.bookmyhotel.entity.Booking;
import com.bookmyhotel.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByPropertyUser(PropertyUser propertyUser);
}