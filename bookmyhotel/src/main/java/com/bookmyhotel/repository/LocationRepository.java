package com.bookmyhotel.repository;

import com.bookmyhotel.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}