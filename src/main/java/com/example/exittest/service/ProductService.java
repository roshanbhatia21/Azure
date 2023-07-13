package com.example.exittest.service;

import java.util.List;

import com.example.exittest.model.Product;

public interface ProductService {
	List<Product> getAllProducts();

	Product createProduct(Product product);

	long countProducts();

	List<Product> searchProducts(String name, String code, String brand);

	Product getProductById(long id);
}
