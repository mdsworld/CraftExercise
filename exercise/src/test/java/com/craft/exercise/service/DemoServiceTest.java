package com.craft.exercise.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.craftexercise.entity.DemoRepository;
import com.craftexercise.entity.Item;
import com.craftexercise.entity.Order;
import com.craftexercise.entity.Promo;
import com.craftexercise.service.DemoService;


/**
 * Unit test for DemoService.
 */
public class DemoServiceTest {
    
    DemoService service = new DemoService();

    @Mock
    protected DemoRepository repository = mock(DemoRepository.class);;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service.repository = repository;
    }

    //*****************************
    //      Test cases
    //*****************************
    
    @Test
    public void testGetPromoByCodePositive() throws Exception{
        Promo prom = new Promo();
        when(repository.findPromoByCode(any())).thenReturn(prom);
        Promo resp = service.getPromoByCode("promo");
        //Validate response
        Assert.assertNotNull(resp);
    }

    @Test(expectedExceptions={Exception.class},expectedExceptionsMessageRegExp = "Promo not found for promocode-promo")
    public void testGetPromoByCodeNegative() throws Exception{
        when(repository.findPromoByCode(any())).thenReturn(null);
        service.getPromoByCode("promo");
    }
    
    @Test
    public void testAddPromoPositive(){
        when(repository.addPromo(any())).thenReturn(1);
        String resp = service.addPromo(new Promo());
        //Validate response
        Assert.assertNotNull(resp);
        Assert.assertEquals(resp, "Added Successfully");
    }

    @Test
    public void testAddPromoNegative(){
        when(repository.addPromo(any())).thenReturn(0);
        String resp = service.addPromo(new Promo());
        //Validate response
        Assert.assertNotNull(resp);
        Assert.assertEquals(resp, "request failed");
    }
    
    @Test
    public void testGetItemPositive() {
        Item item = new Item();
        item.setCategory("category");
        item.setItemId(100);
        item.setName("name");
        item.setPrice(new java.math.BigDecimal(20));
        when(repository.findItemById(any())).thenReturn(item);
        Item resp = service.getItem(100);

        //Validate response
        Assert.assertNotNull(resp);
        Assert.assertEquals(resp.getCategory(), item.getCategory());
        Assert.assertEquals(resp.getName(), item.getName());
        Assert.assertEquals(resp.getItemId(), item.getItemId());
        Assert.assertEquals(resp.getPrice(), item.getPrice());
        Assert.assertEquals(resp.getQuantity(), item.getQuantity());
    }

    @Test
    public void testGetItemNegative() throws Exception {
        when(repository.findItemById(any())).thenReturn(null);
        Item resp = service.getItem(0);
        //Validate response
        Assert.assertNull(resp);
    }
    
    //**********    Order calculate total amt *************
    @Test(testName="Order Positive")
    public void testCalculateTotalAmt() throws Exception {
        Item item = new Item();
        item.setCategory("category");
        item.setItemId(100);
        item.setName("name");
        item.setPrice(new java.math.BigDecimal(3.6));
        
        Item item2 = new Item();
        item2.setCategory("category");
        item2.setItemId(101);
        item2.setName("name");
        item2.setPrice(new java.math.BigDecimal(2.2));

        when(repository.findItemById(eq(100))).thenReturn(item);
        when(repository.findItemById(eq(101))).thenReturn(item2);

        Set<Integer> itemIds = new HashSet<>();
        itemIds.add(100);itemIds.add(101);
        
        BigDecimal resp = service.calculateTotalAmt(itemIds,null);
        //Validate response
        Assert.assertNotNull(resp);
        Assert.assertEquals(resp, item.getPrice().add(item2.getPrice()));
    }
    
    @Test(testName="Order Positive")
    public void testCalculateTotalAmtWithPromo() throws Exception {
        Item item = new Item();
        item.setCategory("category");
        item.setItemId(100);
        item.setName("name");
        item.setPrice(new java.math.BigDecimal(3.6));
        
        Item item2 = new Item();
        item2.setCategory("category");
        item2.setItemId(101);
        item2.setName("name");
        item2.setPrice(new java.math.BigDecimal(2.2));

        when(repository.findItemById(eq(100))).thenReturn(item);
        when(repository.findItemById(eq(101))).thenReturn(item2);
        
        Promo promo = new Promo();
        promo.setPromoAmt(new BigDecimal(3));
        promo.setPromoId(200);
        promo.setPromoCode("promocode");
        
        when(repository.findPromoByCode(any())).thenReturn(promo);

        Set<Integer> itemIds = new HashSet<>();
        itemIds.add(100);itemIds.add(101);
        
        BigDecimal resp = service.calculateTotalAmt(itemIds,"promocode");
        //Validate response
        BigDecimal amt = item.getPrice().add(item2.getPrice());
        amt = amt.subtract(promo.getPromoAmt());
        Assert.assertNotNull(resp);
        Assert.assertEquals(resp,amt);
    }

    @Test(testName="Order Negative", expectedExceptions={Exception.class},expectedExceptionsMessageRegExp="Promocode invalid- promocode")
    public void testCalculateTotalAmtWithInvalidPromo() throws Exception {
        Item item = new Item();
        item.setCategory("category");
        item.setItemId(100);
        item.setName("name");
        item.setPrice(new java.math.BigDecimal(3.6));

        when(repository.findItemById(any())).thenReturn(item);
        when(repository.findPromoByCode(any())).thenReturn(null);

        Set<Integer> itemIds = new HashSet<>();
        itemIds.add(100);itemIds.add(101);
        
        service.calculateTotalAmt(itemIds,"promocode");
    }
    
    @Test(testName="Order Negative", expectedExceptions={Exception.class},expectedExceptionsMessageRegExp="Item not found for Id- 100")
    public void testCalculateTotalAmtWithInvalidItem() throws Exception {
        when(repository.findItemById(any())).thenReturn(null);

        Set<Integer> itemIds = new HashSet<>();
        itemIds.add(100);itemIds.add(101);
        
        service.calculateTotalAmt(itemIds,"promocode");
    }
    
    //**********    Order confirm order *************
    @Test(testName="Order Positive")
    public void testConfirmOrderPositive() throws Exception {
        Item item = new Item();
        item.setCategory("category");
        item.setItemId(100);
        item.setName("name");
        item.setQuantity(20L);
        item.setPrice(new java.math.BigDecimal(3.6));
        
        Item item2 = new Item();
        item2.setCategory("category");
        item2.setItemId(101);
        item2.setName("name");
        item2.setQuantity(20L);
        item2.setPrice(new java.math.BigDecimal(2.2));
        
        Set<Integer> itemIds = new HashSet<>();
        itemIds.add(100);itemIds.add(101);
        
        Order order = new Order();
        order.setItemIds(itemIds.toString());
        order.setOrderNumber("20202");
        order.setTotalPrice(item2.getPrice());
        order.setOrderId(20202);
        
        when(repository.findItemById(eq(100))).thenReturn(item);
        when(repository.findItemById(eq(101))).thenReturn(item2);
        when(repository.addOrder(anyObject())).thenReturn(order);

        Order resp = service.placeOrder(itemIds,null);
        //Validate response
        Assert.assertNotNull(resp);
        Assert.assertNotNull(resp.getOrderId());
        Assert.assertNotNull(resp.getOrderNumber());
        Assert.assertEquals(resp.getTotalPrice(), order.getTotalPrice());
    }
    
    @Test(testName="Order Positive")
    public void testConfirmOrderWithPromo() throws Exception {
        Item item = new Item();
        item.setCategory("category");
        item.setItemId(100);
        item.setName("name");
        item.setQuantity(20L);
        item.setPrice(new java.math.BigDecimal(3.6));
        
        Item item2 = new Item();
        item2.setCategory("category");
        item2.setItemId(101);
        item2.setName("name");
        item2.setQuantity(20L);
        item2.setPrice(new java.math.BigDecimal(2.2));
        when(repository.findItemById(eq(100))).thenReturn(item);
        when(repository.findItemById(eq(101))).thenReturn(item2);
        
        Set<Integer> itemIds = new HashSet<>();
        itemIds.add(100);itemIds.add(101);
        
        Order order = new Order();
        order.setItemIds(itemIds.toString());
        order.setOrderNumber("20202");
        order.setTotalPrice(item2.getPrice());
        order.setOrderId(20202);        
        when(repository.addOrder(anyObject())).thenReturn(order);
        
        Promo promo = new Promo();
        promo.setPromoAmt(new BigDecimal(3));
        promo.setPromoId(200);
        promo.setPromoCode("promocode");
        when(repository.findPromoByCode(any())).thenReturn(promo);
                
        Order resp = service.placeOrder(itemIds,"promocode");
        //Validate response
        Assert.assertNotNull(resp);
        Assert.assertNotNull(resp.getOrderId());
        Assert.assertNotNull(resp.getOrderNumber());
        Assert.assertEquals(resp.getTotalPrice(), order.getTotalPrice());
    }

    @Test(testName="Order Negative", expectedExceptions={Exception.class},expectedExceptionsMessageRegExp="Promocode invalid - promocode")
    public void testConfirmOrderWithInvalidPromo() throws Exception {
        Item item = new Item();
        item.setCategory("category");
        item.setItemId(100);
        item.setQuantity(20L);
        item.setName("name");
        item.setPrice(new java.math.BigDecimal(3.6));

        when(repository.findItemById(any())).thenReturn(item);
        when(repository.findPromoByCode(any())).thenReturn(null);

        Set<Integer> itemIds = new HashSet<>();
        itemIds.add(100);itemIds.add(101);
        
        service.placeOrder(itemIds,"promocode");
    }
    
    @Test(testName="Order Negative", expectedExceptions={Exception.class},expectedExceptionsMessageRegExp="Item not available - 100")
    public void testConfirmOrderWithInvalidItem() throws Exception {
        when(repository.findItemById(any())).thenReturn(null);

        Set<Integer> itemIds = new HashSet<>();
        itemIds.add(100);itemIds.add(101);
        
        service.placeOrder(itemIds,"promocode");
    }

}
