package com.example.exittest.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exittest.model.Product;
import com.example.exittest.model.Review;
import com.example.exittest.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private ProductService productService;

	public Review addReviews(Review review, int productId) {
		Product product = productService.getProductById(productId);
		product.getReview().add(review);
		productService.createProduct(product);

		review.setProduct(product);
		reviewRepository.save(review);

		return review;
	}

	/**
	 * Retrieves all reviews.
	 *
	 * @return a list of all reviews
	 */
	public List<Review> getReview() {
		return (List<Review>) reviewRepository.findAll();
	}

	/**
	 * Gets the number of reviews.
	 *
	 * @return the total number of reviews
	 */
	public Long getNoOfReviews() {
		return reviewRepository.count();
	}

	/**
	 * Retrieves reviews for a specific product.
	 *
	 * @param productId the ID of the product
	 * @return a list of reviews for the product
	 * @throws NoSuchElementException if the product is not found
	 */
	public List<Review> getReviewsByProduct(Long productId) {
		try {
			Product product = productService.getProductById(productId);
			return product.getReview();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Product not found with ID: " + productId);
		}
	}

	/**
	 * Calculates the average rating for a product.
	 *
	 * @param productId the ID of the product
	 * @return the average rating
	 * @throws NoSuchElementException if the product is not found
	 */
	public double getAverageRating(Long productId) {
		try {
			Product product = productService.getProductById(productId);
			List<Review> reviews = product.getReview();
			int totalRatings = 0;

			for (Review review : reviews) {
				totalRatings += Integer.parseInt(review.getRating());
			}

			if (reviews.size() > 0) {
				double averageRating = (double) totalRatings / reviews.size();
				DecimalFormat df = new DecimalFormat("#.##");
				return Double.parseDouble(df.format(averageRating));
			}
			return 0.0;
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Product not found with ID: " + productId);
		}
	}
}
