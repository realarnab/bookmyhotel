package com.bookmyhotel.repository;

import com.bookmyhotel.entity.Favourite;
import com.bookmyhotel.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite,Long> {

    List<Favourite> findByPropertyUser(PropertyUser user);
}
