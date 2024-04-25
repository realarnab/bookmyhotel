package com.bookmyhotel.repository;

import com.bookmyhotel.entity.Booking;
import com.bookmyhotel.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByPropertyUser(PropertyUser propertyUser);

    @Query("SELECT COUNT(b) = 0 FROM Booking b " +
            "WHERE b.property.id = :propertyId " +
            "AND NOT (:checkInDate >= b.checkOutDate OR :checkOutDate <= b.checkInDate)")
    boolean checkAvailability(@Param("propertyId") long propertyId,
                              @Param("checkInDate") LocalDate checkInDate,
                              @Param("checkOutDate") LocalDate checkOutDate);

}