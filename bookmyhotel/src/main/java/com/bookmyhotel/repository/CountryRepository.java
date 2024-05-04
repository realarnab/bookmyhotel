package com.bookmyhotel.repository;

import com.bookmyhotel.entity.Country;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    @Transactional
    @Modifying // Apply this annotation to indicate update operation
    @Query("UPDATE Country c SET c.countryName = :countryName WHERE c.id = :id")
    void updateName(@Param("id") long id,@Param("countryName") String countryName);
}