package com.example.Microservices.ProductService.Services;

import com.example.Microservices.ProductService.Model.ProductResponse;
import org.springframework.stereotype.Service;

import com.example.Microservices.ProductService.Model.ProductRequest;

import java.util.List;

@Service
public interface ProductServiceImpl {

	public ProductResponse addProduct(ProductRequest requestBody);
	public ProductResponse getProductsById(Long id);
	public List<ProductResponse> getAllProducts();
	public void reductQuantity(long id, long quantity);

}
