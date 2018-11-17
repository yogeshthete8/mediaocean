package com.ors.retailshop.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bill_item")
public class BillItem {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @OneToOne(fetch = FetchType.EAGER)
  private ProductEntity product;

  private long quantity;

  public BillItem() {
    super();
  }

  public BillItem(ProductEntity product, int quantity) {
    super();
    this.product = product;
    this.quantity = quantity;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public ProductEntity getProduct() {
    return product;
  }

  public long getQuantity() {
    return quantity;
  }

  public void setProduct(ProductEntity product) {
    this.product = product;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }

}
