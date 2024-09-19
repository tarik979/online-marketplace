package com.online_marketplace.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online_marketplace.dto.ReviewDto;
import com.online_marketplace.mapper.ReviewMapper;
import com.online_marketplace.model.LocalUser;
import com.online_marketplace.model.Product;
import com.online_marketplace.model.Review;
import com.online_marketplace.repository.ProductRepository;
import com.online_marketplace.repository.UserRespository;
import com.online_marketplace.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ProductRepository productRepository;
    private final UserRespository userRespository;


    @PostMapping("/add")
public ResponseEntity<ReviewDto> addReview(@RequestBody ReviewDto reviewDTO) {

    Product product = productRepository.findById(reviewDTO.getProductId()).get();
    LocalUser user = userRespository.findById(reviewDTO.getUserId()).get();

    if (product == null || user == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    Review review = new Review();
    review.setRating(reviewDTO.getRating());
    review.setComment(reviewDTO.getComment());
    review.setProduct(product);
    review.setUser(user);
    

    Review createdReview = reviewService.addReview(review);

    ReviewDto newReviewDto = new ReviewDto();
    newReviewDto.setUserId(createdReview.getUser().getUserId());
    newReviewDto.setComment(createdReview.getComment());
    newReviewDto.setProductId(createdReview.getProduct().getProductId());
    newReviewDto.setRating(createdReview.getRating());
    newReviewDto.setReviewId(createdReview.getReviewId());
    return new ResponseEntity<>(newReviewDto, HttpStatus.CREATED);
}

    @GetMapping("/find/all")
    public ResponseEntity<List<ReviewDto>> findAllReviews(){
        List<Review> reviews = reviewService.findAll();
        if(reviews.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>( new ReviewMapper().toDtoList(reviews), HttpStatus.OK);
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

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteReviewById(@PathVariable("id") Long id){
        reviewService.deleteReviewById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
