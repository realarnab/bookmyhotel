package com.bookmyhotel.controller;

import com.bookmyhotel.dto.PropertyDto;
import com.bookmyhotel.dto.ReviewDto;
import com.bookmyhotel.entity.Property;
import com.bookmyhotel.entity.PropertyUser;
import com.bookmyhotel.entity.Review;
import com.bookmyhotel.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

   private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/addReview/{propertyId}") //this method is used for creating the review , A review can be created by the active user/ login user only, that's why we used @AuthenticationPrinciple
    public ResponseEntity<String> addReview(@PathVariable long propertyId, @RequestBody Review review, @AuthenticationPrincipal PropertyUser propertyUser){
//        Optional<Property> opProperty = propertyRepository.findById(propertyId); //firstly checked that property is present or not for which review be created using propertyId
//        Property property = opProperty.get(); //convert it to the entity object
//
//        Review r = reviewRepository.findReviewByUser(property, propertyUser); //this method will check is there any review present of this user on this  property or not based on userId and propertyId will be extract from property object and propertyUser object
//        if (r!=null){ //if present then it will return the response to  the client
//            return new ResponseEntity<>("You have already added a review for this property",HttpStatus.OK);
//        }
//        //if any review is not present of the user then it will create the review of the user using following steps
//        review.setProperty(property); //set the property details to the review object
//        review.setPropertyUser(propertyUser); //set the user details in the review object and both
//        reviewRepository.save(review); //save the details
//        return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        boolean status = reviewService.createReview(propertyId, review, propertyUser);
        if (status){
            return new ResponseEntity<>("Review added successfully",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("You have already added a review for this property",HttpStatus.OK);
    }

    @GetMapping("/userReviews") //to get reviews given by the current user logged in
    public ResponseEntity<List<Review>> findReviewsOfUser(@AuthenticationPrincipal PropertyUser user){
//        List<Review> reviews = reviewRepository.findReviewsByPropertyUser(user);
        List<Review> reviewsOfUser = reviewService.getReviewsOfUser(user);
        return new ResponseEntity<>(reviewsOfUser,HttpStatus.OK);
    }

    @DeleteMapping("/deleteReview/{id}")
    public ResponseEntity<?> removeReview(@PathVariable long id,@AuthenticationPrincipal PropertyUser user){
        boolean status = reviewService.deleteReview(id, user);
        if (status) {
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review not found",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/propertyReviews/{propertyName}")
    public ResponseEntity<?> getPropertyReview(@PathVariable String propertyName){
        List<ReviewDto> dtos = reviewService.getReviewsOfProperty(propertyName);
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }
}
