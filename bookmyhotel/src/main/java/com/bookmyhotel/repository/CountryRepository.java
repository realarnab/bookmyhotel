package com.bookmyhotel.repository;

import com.bookmyhotel.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

}