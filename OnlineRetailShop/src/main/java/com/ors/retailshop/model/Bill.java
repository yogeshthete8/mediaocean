package com.ors.retailshop.model;

import java.util.List;

public class Bill {

  private Long billId;

  private List<ProductOrder> addedProducts;

  private List<ProductOrder> removedProducts;

  public Long getBillId() {
    return billId;
  }

  public void setBillId(Long billId) {
    this.billId = billId;
  }

  public Bill() {
    super();
  }

  public List<ProductOrder> getAddedProducts() {
    return addedProducts;
  }

  public void setAddedProducts(List<ProductOrder> addedProducts) {
    this.addedProducts = addedProducts;
  }

  public List<ProductOrder> getRemovedProducts() {
    return removedProducts;
  }

  public void setRemovedProducts(List<ProductOrder> removedProducts) {
    this.removedProducts = removedProducts;
  }

}
