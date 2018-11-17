package com.ors.retailshop;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ors.retailshop.constants.Category;
import com.ors.retailshop.entity.BillEntity;
import com.ors.retailshop.model.Bill;
import com.ors.retailshop.model.Product;
import com.ors.retailshop.model.ProductOrder;
import com.ors.retailshop.service.BillService;
import com.ors.retailshop.service.ProductService;

@Component
public class OnlineRetailShopRunner implements CommandLineRunner {

	@Autowired
	private BillService billService;

	@Autowired
	private ProductService productService;

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void run(String... arg0) throws Exception {
		addProductsInInventory();
		createBill();
	}

	public void createBill() {
		Bill bill = new Bill();
		//Add new products in cart
		List<ProductOrder> productsList1 = new ArrayList<>(); 
		productsList1.add(new ProductOrder("1", 1));
		productsList1.add(new ProductOrder("2", 1));
		productsList1.add(new ProductOrder("3", 2));
		bill.setAddedProducts(productsList1);
		BillEntity billEntity = billService.createBill(bill);
		logger.info("Bill is created with id -  {}", billEntity.getId());
		logger.info("Total amount = {}", billEntity.getTotalAmount());
		logger.info("Total cost = {}", billEntity.getTotalCost());
		logger.info("Total sales tax = {}", billEntity.getTotalSalesTax());
	}

	private void addProductsInInventory() {
		productService.createProduct(new Product("1", 150.0, "TV", Category.A));
		productService.createProduct(new Product("2", 70.0, "iPhone", Category.B));
		productService.createProduct(new Product("3", 24.0, "headset", Category.C));
	}
}
