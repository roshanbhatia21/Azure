//package com.example.exittest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.example.exittest.model.Product;
//import com.example.exittest.model.Review;
//import com.example.exittest.model.User;
//import com.example.exittest.repository.ProductRepository;
//import com.example.exittest.repository.ReviewRepository;
//import com.example.exittest.repository.UserRepository;
//import com.example.exittest.service.Productservice;
//import com.example.exittest.service.ReviewService;
//import com.example.exittest.service.UserService;
//
//@SpringBootTest
//class ExittestApplicationTests {
//
//	@Autowired
//	private Productservice productService;
//
//	@Autowired
//	private ReviewService reviewService;
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private ProductRepository productRepository;
//
//	@Autowired
//	private ReviewRepository reviewRepository;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Test
//	void contextLoads() {
//		// Basic test to check if the application context loads successfully
//	}
//
//	@Test
//	void testSearchProducts() {
//		String name = "Sample";
//		String code = "123";
//		String brand = "Brand";
//
//		// Create sample products
//		Product product1 = new Product();
//		product1.setId(1L);
//		product1.setProductName("Sample Product 1");
//		product1.setProductBrand("Brand A");
//		product1.setProductCode("ABC123");
//
//		Product product2 = new Product();
//		product2.setId(2L);
//		product2.setProductName("Sample Product 2");
//		product2.setProductBrand("Brand B");
//		product2.setProductCode("DEF456");
//
//		// Add the sample products to the list
//		List<Product> productList = new ArrayList<>();
//		productList.add(product1);
//		productList.add(product2);
//
//		// Set the mocked behavior
//		when(productRepository
//				.findByProductNameContainingIgnoreCaseAndProductCodeContainingIgnoreCaseAndProductBrandContainingIgnoreCase(
//						name, code, brand))
//				.thenReturn(productList);
//
//		List<Product> result = productService.searchProducts(name, code, brand);
//
//		assertEquals(productList, result);
//	}
//
//	@Test
//	void testAddReviews() {
//		Review review = new Review();
//		int productId = 1;
//		Product product = new Product();
//		product.setId(productId);
//		product.setProductName("Sample Product");
//		product.setProductBrand("Brand A");
//		product.setProductCode("ABC123");
//
//		// Set review properties
//		review.setReviewId(1);
//		review.setReview("Great product!");
//		review.setRating("5");
//		review.setProduct(product);
//
//		// Set the mocked behavior
//		when(productService.getProductById(productId)).thenReturn(product);
//		when(productService.createProduct(product)).thenReturn(product);
//
//		Review result = reviewService.addReviews(review, productId);
//
//		assertNotNull(result);
//		assertEquals(review, result);
//	}
//
//	@Test
//	void testGetAverageRating() {
//	    // Arrange
//	    long productId = 1L;
//	    Product product = new Product();
//	    product.setId(productId);
//	    product.setProductName("Sample Product");
//	    product.setProductBrand("Brand A");
//	    product.setProductCode("ABC123");
//
//	    // Create sample reviews
//	    Review review1 = new Review();
//	    review1.setReviewId(1);
//	    review1.setReview("Great product!");
//	    review1.setRating("4");
//	    review1.setProduct(product);
//
//	    Review review2 = new Review();
//	    review2.setReviewId(2);
//	    review2.setReview("Excellent quality!");
//	    review2.setRating("5");
//	    review2.setProduct(product);
//
//	    List<Review> reviewList = new ArrayList<>();
//	    reviewList.add(review1);
//	    reviewList.add(review2);
//
//	    // Set the product's review list
//	    product.setReview(reviewList);
//
//	    // Mock the productService.getProductById method
//	    when(productService.getProductById(productId)).thenReturn(product);
//
//	    // Act
//	    double result = reviewService.getAverageRating(productId);
//
//	    // Assert
//	    assertEquals(4.5, result);
//	}
//
//
//	@Test
//	void testLoginUser() {
//		String email = "test@example.com";
//		String password = "password";
//
//		User user = new User();
//		// Set user properties
//		user.setEmailId(email);
//		user.setPassword(password);
//
//		// Add the sample user to the UserRepository
//		when(userRepository.findByEmailId(email)).thenReturn(user);
//
//		boolean result = userService.loginUser(email, password);
//
//		assertEquals(true, result);
//	}
//
//	@Test
//	void testRegisterUser() {
//		String email = "test@example.com";
//		String firstName = "John";
//		String lastName = "Doe";
//		String password = "password";
//
//		User newUser = new User();
//		// Set user properties
//		newUser.setEmailId(email);
//		newUser.setFirstName(firstName);
//		newUser.setLastName(lastName);
//		newUser.setPassword(password);
//
//		// Set the mocked behavior
//		when(userRepository.findByEmailId(email)).thenReturn(null);
//		when(userRepository.save(newUser)).thenReturn(newUser);
//
//		User result = userService.registerUser(email, firstName, lastName, password);
//
//		assertEquals(newUser, result);
//	}
//}
