package com.ors.retailshop.model;

import javax.validation.constraints.NotNull;

public class ProductOrder {

  @NotNull
  private String code;

  private int quantity;

  public ProductOrder(String code, int quantity) {
    this.code = code;
    this.quantity = quantity;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

}
