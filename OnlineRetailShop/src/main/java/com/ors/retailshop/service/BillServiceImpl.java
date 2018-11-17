package com.ors.retailshop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.annotations.VisibleForTesting;
import com.ors.retailshop.constants.Category;
import com.ors.retailshop.entity.BillEntity;
import com.ors.retailshop.entity.BillItem;
import com.ors.retailshop.entity.ProductEntity;
import com.ors.retailshop.exception.ORSException;
import com.ors.retailshop.model.Bill;
import com.ors.retailshop.model.ProductOrder;
import com.ors.retailshop.repository.BillRepository;

@Service
@Transactional
@Configuration
public class BillServiceImpl implements BillService {

  @Autowired
  private BillRepository billRepository;
  @Autowired
  private ProductService productService;

  @Value("${product.category.a.lavy:10}")
  private Integer categoryA;

  @Value("${product.category.b.lavy:20}")
  private Integer categoryB;

  @Value("${product.category.c.lavy:0}")
  private Integer categoryC;

  final Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);
  public static final String ERROR_CODE = "Product with code does not exist";
  public static final String ERROR_BILL = "Bill not found";
  private static final String ERROR_CREATE_BILL = "Please provide required information to create a bill";

  public Iterable<BillEntity> getAllBills() {
    logger.info("Returning all the bills");
    return billRepository.findAll();
  }

  public BillEntity getBillById(Long id) {
    return billRepository.findById(id)
        .orElseThrow(() -> new ORSException(ERROR_BILL + " for id " + id));
  }

  @Override
  public BillEntity createBill(Bill bill) {
    if (null == bill || CollectionUtils.isEmpty(bill.getAddedProducts())) {
      throw new ORSException(ERROR_CREATE_BILL);
    }
    List<ProductOrder> products = bill.getAddedProducts();
    BillEntity billEntity = new BillEntity();
    List<BillItem> items = new ArrayList<>();
    products.stream().forEach(po -> {
      ProductEntity selectedProduct = isProductExists(po.getCode());
      BillItem item = new BillItem(selectedProduct, po.getQuantity());
      items.add(item);
    });
    billEntity.setItems(items);
    billEntity.setDate(new Date());
    calculateTotalAmount(billEntity);
    billEntity = billRepository.save(billEntity);
    logger.info("Successfully created an bill with id {}", billEntity.getId());
    return billEntity;
  }

  private BillEntity calculateTotalAmount(BillEntity billEntity) {
    double totalCost = 0.0;
    double totalTax = 0.0;
    if (!CollectionUtils.isEmpty(billEntity.getItems())) {
      List<BillItem> items = billEntity.getItems();
      for (BillItem item : items) {
        double salesTax = calculateSalesTaxForItem(item);
        double itemCost = item.getQuantity() * item.getProduct().getPrice();
        totalCost += itemCost;
        totalTax += salesTax;
      }
    }
    billEntity.setTotalCost(totalCost);
    billEntity.setTotalAmount(totalCost + totalTax);
    billEntity.setTotalSalesTax(totalTax);
    return billEntity;
  }

  private double calculateSalesTaxForItem(BillItem item) {
    long quantity = item.getQuantity();
    Category category = item.getProduct().getCategory();
    double price = item.getProduct().getPrice();
    double salesTax = 0;
    switch (category) {
    case A:
      salesTax = calculateSalesTax(quantity, price, categoryA);
      break;
    case B:
      salesTax = calculateSalesTax(quantity, price, categoryB);
      break;
    case C:
      salesTax = calculateSalesTax(quantity, price, categoryC);
      break;
    default:
      break;
    }
    return salesTax;
  }

  private double calculateSalesTax(long quantity, double price, Integer categoryLavy) {
    double lavy = (double) categoryLavy / 100;
    return quantity * price * lavy;
  }

  private ProductEntity isProductExists(String barCode) {
    ProductEntity product = productService.getProductByCode(barCode);
    if (Objects.isNull(product)) {
      throw new ORSException(ERROR_CODE);
    }
    return product;
  }

  public Integer getCategoryA() {
    return categoryA;
  }

}
