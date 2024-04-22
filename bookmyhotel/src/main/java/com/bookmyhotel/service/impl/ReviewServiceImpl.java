package com.bookmyhotel.service.impl;

import com.bookmyhotel.dto.ReviewDto;
import com.bookmyhotel.entity.Property;
import com.bookmyhotel.entity.PropertyUser;
import com.bookmyhotel.entity.Review;
import com.bookmyhotel.repository.PropertyRepository;
import com.bookmyhotel.repository.ReviewRepository;
import com.bookmyhotel.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl  implements ReviewService {
    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }


    @Override
    public boolean createReview(long propertyId, Review review, PropertyUser user) {
        Optional<Property> opProperty = propertyRepository.findById(propertyId); //firstly checked that property is present or not for which review be created using propertyId
        Property property = opProperty.get(); //convert it to the entity object

        Review r = reviewRepository.findReviewByUser(property, user); //this method will check is there any review present of this user on this  property or not based on userId and propertyId will be extract from property object and propertyUser object
        if (r != null) { //if present then it will return the response to  the client
            return false;
        }
        //if any review is not present of the user then it will create the review of the user using following steps
        review.setProperty(property); //set the property details to the review object
        review.setPropertyUser(user); //set the user details in the review object and both
        reviewRepository.save(review); //save the details
        return true;
    }

    @Override
    public List<Review> getReviewsOfUser(PropertyUser user) {
        List<Review> reviews = reviewRepository.findReviewsByPropertyUser(user);
        return reviews;
    }
}
