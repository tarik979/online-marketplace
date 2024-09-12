package com.online_marketplace.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online_marketplace.model.Review;
import com.online_marketplace.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    public ResponseEntity<Review> addReview(@RequestBody Review review){
        Review createdReview = reviewService.addReview(review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Review>> findAllReviews(){
        List<Review> reviews = reviewService.findAll();
        if(reviews.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Review> findReviewById(@PathVariable("id") Long id){
        Review review = reviewService.findReviewById(id);
        if (review == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Review> updateReview(@RequestBody Review review){
        Review updatedReview = reviewService.updatReview(review);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteReviewById(@PathVariable("id") Long id){
        reviewService.deleteReviewById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
