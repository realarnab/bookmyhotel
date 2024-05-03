package com.bookmyhotel.service;

import com.bookmyhotel.dto.ReviewDto;
import com.bookmyhotel.entity.PropertyUser;
import com.bookmyhotel.entity.Review;

import java.util.List;

public interface ReviewService {
public boolean createReview(long propertyId, Review review, PropertyUser user);
public List<Review> getReviewsOfUser(PropertyUser user);

    boolean deleteReview(long id, PropertyUser user);

    List<ReviewDto> getReviewsOfProperty(String propertyName);
}
