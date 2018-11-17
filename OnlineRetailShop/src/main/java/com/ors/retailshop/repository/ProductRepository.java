package com.ors.retailshop.repository;

import org.springframework.data.repository.CrudRepository;

import com.ors.retailshop.entity.ProductEntity;


public interface ProductRepository extends CrudRepository<ProductEntity, Long>{
	
	public ProductEntity findByCode(String code);

}
