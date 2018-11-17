package com.ors.retailshop.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.ors.retailshop.constants.Category;

public class Product {

  @NotNull
  private String code;

  @NotNull
  private String name;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Category productCategory;

  @NotNull
  private double price;

  public Product(String code, double price, String name, Category productCategory) {
    super();
    this.code = code;
    this.name = name;
    this.productCategory = productCategory;
    this.price = price;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Category getProductCategory() {
    return productCategory;
  }

  public void setProductCategory(Category productCategory) {
    this.productCategory = productCategory;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
