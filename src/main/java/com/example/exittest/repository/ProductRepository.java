package com.example.exittest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.exittest.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByProductNameContainingIgnoreCase(String name);

	List<Product> findByProductCodeContainingIgnoreCase(String code);

	List<Product> findByProductBrandContainingIgnoreCase(String brand);

	List<Product> findByProductNameContainingIgnoreCaseAndProductCodeContainingIgnoreCaseAndProductBrandContainingIgnoreCase(
			String name, String code, String brand);

	List<Product> findByProductNameContainingIgnoreCaseAndProductCodeContainingIgnoreCase(String name, String code);

	List<Product> findByProductNameContainingIgnoreCaseAndProductBrandContainingIgnoreCase(String name, String brand);

	List<Product> findByProductCodeContainingIgnoreCaseAndProductBrandContainingIgnoreCase(String code, String brand);

}
