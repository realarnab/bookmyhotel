package com.bookmyhotel.service.impl;

import com.bookmyhotel.dto.ReviewDto;
import com.bookmyhotel.entity.Property;
import com.bookmyhotel.entity.PropertyUser;
import com.bookmyhotel.entity.Review;
import com.bookmyhotel.exceptions.PropertyNotFound;
import com.bookmyhotel.exceptions.ReviewNotFoundException;
import com.bookmyhotel.repository.PropertyRepository;
import com.bookmyhotel.repository.ReviewRepository;
import com.bookmyhotel.service.ReviewService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl  implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final PropertyRepository propertyRepository;
    private final ModelMapper modelMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, PropertyRepository propertyRepository,
                             ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ReviewDto createReview(long propertyId, Review review, PropertyUser user) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new PropertyNotFound("No Property found with this id: " + propertyId));//firstly checked that property is present or not for which review be created using propertyId
//Property property = opProperty.get(); //convert it to the entity object

        Review r = reviewRepository.findReviewByUser(property, user); //this method will check is there any review present of this user on this  property or not based on userId and propertyId will be extract from property object and propertyUser object
        if (r != null) { //if present then it will return the response to  the client
            return null;
        }
        //if any review is not present of the user then it will create the review of the user using following steps
        review.setProperty(property); //set the property details to the review object
        review.setPropertyUser(user); //set the user details in the review object and both
        Review saved = reviewRepository.save(review);//save the details
        return modelMapper.map(saved,ReviewDto.class);
    }

    @Override
    public List<Review> getReviewsOfUser(PropertyUser user) {
        List<Review> reviews = reviewRepository.findReviewsByPropertyUser(user);
        return reviews;
    }

    @Override
    public boolean deleteReview(long id, PropertyUser user) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("No Review Found with id: " + id));

        if (review.getPropertyUser().getId()==user.getId()){
            reviewRepository.delete(review);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public List<ReviewDto> getReviewsOfProperty(String propertyName) {
        List<Review> reviews = reviewRepository.findReviewByPropertyName(propertyName);
        List<ReviewDto> collections = reviews.stream().map((element) -> modelMapper.map(element, ReviewDto.class)).collect(Collectors.toList());
        return collections;
    }

    @Transactional
    @Override
    public ReviewDto upadateAReview(long id, PropertyUser user,ReviewDto dto) {
        Review review = modelMapper.map(dto, Review.class);
        review.setPropertyUser(user);
        List<Review> reviewsByPropertyUser = reviewRepository.findReviewsByPropertyUser(user);
        Iterator<Review> iterator = reviewsByPropertyUser.iterator();
        while (iterator.hasNext()){
            Review next = iterator.next();
            if (next.getId() == id){
//                reviewRepository.deleteById(id);
//                Review saved = reviewRepository.save(review);
                reviewRepository.updateContentOfReview(id, dto.getContent());
                //Review next = iterator.next();
                return modelMapper.map(next,ReviewDto.class);
            }
        }
        return null;
    }
}
