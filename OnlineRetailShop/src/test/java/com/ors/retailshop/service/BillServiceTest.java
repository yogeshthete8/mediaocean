package com.ors.retailshop.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.ors.retailshop.OnlineRetailShopApplication;
import com.ors.retailshop.constants.Category;
import com.ors.retailshop.entity.BillEntity;
import com.ors.retailshop.entity.ProductEntity;
import com.ors.retailshop.model.Bill;
import com.ors.retailshop.model.ProductOrder;
import com.ors.retailshop.repository.BillRepository;

@RunWith(PowerMockRunner.class)
@SpringBootTest(classes = { OnlineRetailShopApplication.class })
@Profile("test")
public class BillServiceTest {

  @InjectMocks
  private BillServiceImpl billServiceImpl;

  @Mock
  private ProductService productService;

  @Mock
  private BillRepository billRepository;

  private Bill bill;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    bill = new Bill();
    bill.setBillId(1L);
    ProductOrder productOrder = new ProductOrder("1", 1);
    List<ProductOrder> addedProducts = new ArrayList<>();
    addedProducts.add(productOrder);
    bill.setAddedProducts(addedProducts);
  }

  @Test(expected = NullPointerException.class)
  public void testCreateInvoive() {
    ProductEntity productEntity = new ProductEntity();
    productEntity.setCategory(Category.A);
    productEntity.setCode("1");
    productEntity.setName("Test");
    productEntity.setPrice(100);
    PowerMockito.when(productService.getProductByCode(Matchers.anyString())).thenReturn(productEntity);
    BillEntity billEntity = billServiceImpl.createBill(bill);
    PowerMockito.when(billRepository.save(Matchers.any())).thenReturn(billEntity);
    assertEquals(10.0, billEntity.getTotalSalesTax());
  }

}
