package com.ors.retailshop.service;

import com.ors.retailshop.entity.ProductEntity;
import com.ors.retailshop.model.Product;

public interface ProductService {

	public Iterable<ProductEntity> getAllProducts();
	
	public ProductEntity getProductById(Long id);
	
	public ProductEntity getProductByCode(String barCode);

	public ProductEntity createProduct(Product productDetails);
}
