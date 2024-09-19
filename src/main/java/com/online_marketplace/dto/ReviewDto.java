package com.online_marketplace.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {
    private long reviewId;
    private Integer rating;
    private String comment;
    private Long productId;
    private Long userId;
}
