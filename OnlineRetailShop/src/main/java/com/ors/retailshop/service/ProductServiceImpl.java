package com.ors.retailshop.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ors.retailshop.entity.ProductEntity;
import com.ors.retailshop.exception.ORSException;
import com.ors.retailshop.model.Product;
import com.ors.retailshop.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

  @Override
  public Iterable<ProductEntity> getAllProducts() {
    logger.info("Fetch all products");
    return productRepository.findAll();
  }

  @Override
  public ProductEntity getProductById(Long id) {
    logger.info("Fetch product by id {}", id);
    return productRepository.findById(id).orElseThrow(() -> new ORSException("Product not found for the Id "+ id));
  }

  public ProductEntity createProduct(Product productDetails) {
    ProductEntity product = new ProductEntity();
    product.setCode(productDetails.getCode());
    product.setName(productDetails.getName());
    product.setCategory(productDetails.getProductCategory());
    product.setPrice(productDetails.getPrice());
    product = productRepository.save(product);
    logger.info("Product is created successfully with name {}", product.getName().toUpperCase());
    return product;

  }

  @Override
  public ProductEntity getProductByCode(String barCode) {
    return productRepository.findByCode(barCode);
  }

}
