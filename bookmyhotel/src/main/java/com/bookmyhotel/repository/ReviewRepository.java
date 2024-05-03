package com.bookmyhotel.repository;

import com.bookmyhotel.entity.Property;
import com.bookmyhotel.entity.PropertyUser;
import com.bookmyhotel.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.property=:property and r.propertyUser=:propertyUser")
    Review findReviewByUser(@Param("property") Property property, @Param("propertyUser") PropertyUser propertyUser); //to find the review based on the property and user

    @Query("select r from Review r where r.propertyUser=:user")
    List<Review> findReviewsByPropertyUser(@Param("user") PropertyUser user); //to find the reviews given by a particular user

    @Query("select r from Review r JOIN Property p ON r.property=p.id where p.propertyName=:propertyName")
    List<Review> findReviewByPropertyName(@Param("propertyName") String propertyName);
}