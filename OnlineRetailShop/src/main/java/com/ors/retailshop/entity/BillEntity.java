package com.ors.retailshop.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class BillEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private double totalCost;

  private double totalSalesTax;

  private double totalAmount;

  private Date date;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<BillItem> items;

  public BillEntity() {
    super();
  }

  public BillEntity(double totalAmount) {
    super();
    this.totalAmount = totalAmount;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public double getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(double totalCost) {
    this.totalCost = totalCost;
  }

  public double getTotalSalesTax() {
    return totalSalesTax;
  }

  public void setTotalSalesTax(double totalTax) {
    this.totalSalesTax = totalTax;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public List<BillItem> getItems() {
    return items;
  }

  public void setItems(List<BillItem> items) {
    this.items = items;
  }

}
