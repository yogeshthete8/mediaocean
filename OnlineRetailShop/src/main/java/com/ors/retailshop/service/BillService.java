package com.ors.retailshop.service;

import com.ors.retailshop.entity.BillEntity;
import com.ors.retailshop.model.Bill;

public interface BillService {

	public Iterable<BillEntity> getAllBills();
	
	public BillEntity getBillById(Long id);
	
	public BillEntity createBill(Bill bill);

}
