package com.ors.retailshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ors.retailshop.entity.ProductEntity;
import com.ors.retailshop.model.Product;
import com.ors.retailshop.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@ApiOperation(value = "get products list.")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<ProductEntity>> getAllProducts() {
		Iterable<ProductEntity> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@ApiOperation(value = "get product by Id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductEntity> getProductById(
			@ApiParam(value = "Product ID", required = true, defaultValue = "1") @PathVariable Long id) {
		ProductEntity product = productService.getProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Create a new Product")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductEntity> createProduct(
			@ApiParam(value = "New product details", required = true) @RequestBody Product productInfo) {
		ProductEntity product = productService.createProduct(productInfo);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}
}
