package com.example.exittest.service;

import java.util.List;

import com.example.exittest.model.Review;

public interface ReviewService {
	Review addReviews(Review review, int id);

	List<Review> getReviewsByProduct(Long productId);

	Long getNoOfReviews();

	double getAverageRating(Long productId);
}
