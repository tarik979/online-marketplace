package com.online_marketplace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.online_marketplace.exception.ReviewNotFoundexception;
import com.online_marketplace.model.Review;
import com.online_marketplace.repository.ReviewRepository;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    
    public Review addReview(Review review){
        return reviewRepository.save(review);
    }
   
    public List<Review> findAll(){
        return reviewRepository.findAll();
    }
    
    public Review findReviewById(Long id){
        return reviewRepository.findById(id)
        .orElseThrow(()-> new ReviewNotFoundexception("Review By id " + id + " was not found"));
    }
    
    public Review updatReview(Review review){
        return reviewRepository.save(review);
    }
    
    public void deleteReviewById(Long id){
        reviewRepository.deleteById(id);
    }
}
