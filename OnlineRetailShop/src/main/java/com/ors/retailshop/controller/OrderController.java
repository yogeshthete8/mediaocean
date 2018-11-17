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

import com.ors.retailshop.entity.BillEntity;
import com.ors.retailshop.model.Bill;
import com.ors.retailshop.service.BillService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/bill")
public class OrderController {

	@Autowired
	private BillService billService;
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@ApiOperation(value = "Get all the bills.")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<BillEntity>> getBills() {
		logger.info("OrderController :: in getBills");
		return new ResponseEntity<>(billService.getAllBills(), HttpStatus.OK);
	}

	@ApiOperation(value = "Get a bill by Id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BillEntity> getBillById(@PathVariable Long id) {
		logger.info("OrderController :: in getBillById");
		return new ResponseEntity<>(billService.getBillById(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Creates a bill")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BillEntity> createBill(@RequestBody Bill bill) {
		logger.info("OrderController :: in createBill");
		BillEntity billEntity = billService.createBill(bill);
		return new ResponseEntity<>(billEntity, HttpStatus.CREATED);
	}
	
}
