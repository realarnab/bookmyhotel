package com.bookmyhotel.repository;

import com.bookmyhotel.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite,Long> {

}
