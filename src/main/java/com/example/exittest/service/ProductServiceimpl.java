package com.example.exittest.service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.exittest.model.Product;
import com.example.exittest.repository.ProductRepository;

@Service
public class ProductServiceimpl implements ProductService {

	private ProductRepository productRepository;

	public ProductServiceimpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * Retrieves all products.
	 *
	 * @return a list of all products
	 */
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	/**
	 * Creates a new product.
	 *
	 * @param product the product to create
	 * @return the created product
	 */
	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	/**
	 * Counts the number of products.
	 *
	 * @return the total number of products
	 */
	@Override
	public long countProducts() {
		return productRepository.count();
	}

	/**
	 * Searches for products based on the provided parameters.
	 *
	 * @param name  the product name (optional)
	 * @param code  the product code (optional)
	 * @param brand the product brand (optional)
	 * @return a list of matching products, or an empty list if an error occurs
	 *         during the search
	 */
	@Override
	public List<Product> searchProducts(String name, String code, String brand) {
		try {
			if (name != null && !name.isEmpty() && code != null && !code.isEmpty() && brand != null
					&& !brand.isEmpty()) {
				return productRepository
						.findByProductNameContainingIgnoreCaseAndProductCodeContainingIgnoreCaseAndProductBrandContainingIgnoreCase(
								name, code, brand);
			} else if (name != null && !name.isEmpty() && code != null && !code.isEmpty()) {
				return productRepository.findByProductNameContainingIgnoreCaseAndProductCodeContainingIgnoreCase(name,
						code);
			} else if (name != null && !name.isEmpty() && brand != null && !brand.isEmpty()) {
				return productRepository.findByProductNameContainingIgnoreCaseAndProductBrandContainingIgnoreCase(name,
						brand);
			} else if (code != null && !code.isEmpty() && brand != null && !brand.isEmpty()) {
				return productRepository.findByProductCodeContainingIgnoreCaseAndProductBrandContainingIgnoreCase(code,
						brand);
			} else if (name != null && !name.isEmpty()) {
				return productRepository.findByProductNameContainingIgnoreCase(name);
			} else if (code != null && !code.isEmpty()) {
				return productRepository.findByProductCodeContainingIgnoreCase(code);
			} else if (brand != null && !brand.isEmpty()) {
				return productRepository.findByProductBrandContainingIgnoreCase(brand);
			} else {
				return productRepository.findAll();
			}
		} catch (Exception e) {
			System.out.println("An error occurred during product search: " + e.getMessage());
			return Collections.emptyList();
		}
	}

	/**
	 * Retrieves a product by its ID.
	 *
	 * @param id the ID of the product to retrieve
	 * @return the matching product
	 * @throws NoSuchElementException if the product is not found
	 */
	@Override
	public Product getProductById(long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + id));
	}
}
