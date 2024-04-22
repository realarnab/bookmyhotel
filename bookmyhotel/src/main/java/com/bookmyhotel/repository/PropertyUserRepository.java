package com.bookmyhotel.repository;

import com.bookmyhotel.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyUserRepository extends JpaRepository<PropertyUser,Long> {

    Optional<PropertyUser> findByUsername(String username);
}
