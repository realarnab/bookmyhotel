package com.bookmyhotel.repository;

import com.bookmyhotel.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {
   //performing join operation between three tables and make a custom query to fetch the details of properties by location name
    @Query("Select p from Property p JOIN Location l ON p.location=l.id JOIN Country c ON p.country=c.id WHERE l.locationName=:locationName or c.countryName=:locationName")
    List<Property> findPropertyByLocation(@Param("locationName")String locationName); //@Param --> extract the value from the method argument and give it to the custom JPQL query

    Optional<Property> findByPropertyName(String propertyName);
}