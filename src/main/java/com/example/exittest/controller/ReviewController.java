package com.example.exittest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exittest.model.Review;
import com.example.exittest.service.ReviewService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/reviews")
public class ReviewController {

	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@PostMapping("/add/{productId}")
	public ResponseEntity<Review> addReview(@RequestBody Review review, @PathVariable int productId) {
		Review createdReview = reviewService.addReviews(review, productId);
		return ResponseEntity.ok(createdReview);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>> getReviewsByProduct(@PathVariable long productId) {
		List<Review> reviews = reviewService.getReviewsByProduct(productId);
		return ResponseEntity.ok(reviews);
	}

	@GetMapping("/count")
	public ResponseEntity<Long> getNoOfReviews() {
		long count = reviewService.getNoOfReviews();
		return ResponseEntity.ok(count);
	}

	@GetMapping("/average/{productId}")
	public ResponseEntity<Double> getAverageRating(@PathVariable long productId) {
		double averageRating = reviewService.getAverageRating(productId);
		return ResponseEntity.ok(averageRating);
	}
}
