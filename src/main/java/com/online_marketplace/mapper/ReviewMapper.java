package com.online_marketplace.mapper;

import com.online_marketplace.dto.ReviewDto;
import com.online_marketplace.model.Product;
import com.online_marketplace.model.Review;
import com.online_marketplace.model.LocalUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewMapper {

    public ReviewDto toDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReviewId(review.getReviewId());
        reviewDto.setRating(review.getRating());
        reviewDto.setComment(review.getComment());
        reviewDto.setProductId(review.getProduct().getProductId());
        reviewDto.setUserId(review.getUser().getUserId());
        return reviewDto;
    }

    public Review toEntity(ReviewDto reviewDto, Product product, LocalUser user) {
        Review review = new Review();
        review.setReviewId(reviewDto.getReviewId());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setProduct(product);
        review.setUser(user);
        return review;
    }


    public List<ReviewDto> toDtoList(List<Review> reviews) {
        return reviews.stream()
                      .map(this::toDto)
                      .collect(Collectors.toList());
    }

}
